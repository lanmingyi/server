package com.bright.autopoi.cache;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import com.bright.autopoi.cache.manager.POICacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Excel类型的缓存
 */
public final class ExcelCache {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelCache.class);

	public static Workbook getWorkbook(String url, Integer[] sheetNums, boolean needAll) {
		InputStream is = null;
		List<Integer> sheetList = Arrays.asList(sheetNums);
		try {
			is = POICacheManager.getFile(url);
			Workbook wb = WorkbookFactory.create(is);
			// 删除其他的sheet
			if (!needAll) {
				for (int i = wb.getNumberOfSheets() - 1; i >= 0; i--) {
					if (!sheetList.contains(i)) {
						wb.removeSheetAt(i);
					}
				}
			}
			return wb;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				is.close();
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return null;
	}
	// jar 包上传到服务器后 autopoi 读取不到excel模版文件
	public static Workbook getWorkbookByTemplate(String url, Integer[] sheetNums, boolean needAll) {
		List<Integer> sheetList = Arrays.asList(sheetNums);
		InputStream fis = null;
		try {
			// poi3升级到4兼容改造工作
			//ClassPathResource  resource = new ClassPathResource(url);
			fis = new FileInputStream(url);
			LOGGER.info("  >>>  poi3升级到4兼容改造工作, url="+url);
			//fis = resource.getInputStream();
			Workbook wb = WorkbookFactory.create(fis);
			// 删除其他的sheet
			if (!needAll) {
				for (int i = wb.getNumberOfSheets() - 1; i >= 0; i--) {
					if (!sheetList.contains(i)) {
						wb.removeSheetAt(i);
					}
				}
			}
			return wb;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				fis.close();
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return null;
	}
}
