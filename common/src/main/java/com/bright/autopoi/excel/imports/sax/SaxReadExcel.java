package com.bright.autopoi.excel.imports.sax;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import com.bright.autopoi.handler.inter.IExcelReadRowHanlder;
import com.bright.autopoi.excel.entity.ImportParams;
import com.bright.autopoi.excel.imports.sax.parse.ISaxRowRead;
import com.bright.autopoi.excel.imports.sax.parse.SaxRowRead;
import com.bright.autopoi.exception.excel.ExcelImportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * 基于SAX Excel大数据读取,读取Excel 07版本,不支持图片读取
 */
@SuppressWarnings("rawtypes")
public class SaxReadExcel {

	private static final Logger LOGGER = LoggerFactory.getLogger(SaxReadExcel.class);

	public <T> List<T> readExcel(InputStream inputstream, Class<?> pojoClass, ImportParams params, ISaxRowRead rowRead, IExcelReadRowHanlder hanlder) {
		try {
			OPCPackage opcPackage = OPCPackage.open(inputstream);
			return readExcel(opcPackage, pojoClass, params, rowRead, hanlder);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ExcelImportException(e.getMessage());
		}
	}

	private <T> List<T> readExcel(OPCPackage opcPackage, Class<?> pojoClass, ImportParams params, ISaxRowRead rowRead, IExcelReadRowHanlder hanlder) {
		try {
			XSSFReader xssfReader = new XSSFReader(opcPackage);
			SharedStringsTable sst = xssfReader.getSharedStringsTable();
			if (rowRead == null) {
				rowRead = new SaxRowRead(pojoClass, params, hanlder);
			}
			XMLReader parser = fetchSheetParser(sst, rowRead);
			Iterator<InputStream> sheets = xssfReader.getSheetsData();
			int sheetIndex = 0;
			while (sheets.hasNext() && sheetIndex < params.getSheetNum()) {
				sheetIndex++;
				InputStream sheet = sheets.next();
				InputSource sheetSource = new InputSource(sheet);
				parser.parse(sheetSource);
				sheet.close();
			}
			return rowRead.getList();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ExcelImportException("SAX导入数据失败");
		}
	}

	private XMLReader fetchSheetParser(SharedStringsTable sst, ISaxRowRead rowRead) throws SAXException {
		XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
		ContentHandler handler = new SheetHandler(sst, rowRead);
		parser.setContentHandler(handler);
		return parser;
	}

}
