package com.bright.autopoi.excel.export.base;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import com.bright.autopoi.excel.entity.enmus.ExcelType;
import com.bright.autopoi.excel.entity.params.ExcelExportEntity;
import com.bright.autopoi.excel.export.styler.IExcelExportStyler;
import com.bright.autopoi.excel.entity.vo.PoiBaseConstants;
import com.bright.autopoi.exception.excel.ExcelExportException;
import com.bright.autopoi.exception.excel.enums.ExcelExportEnum;
import com.bright.autopoi.util.MyX509TrustManager;
import com.bright.autopoi.util.PoiMergeCellUtil;
import com.bright.autopoi.util.PoiPublicUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 提供POI基础操作服务
 */
public abstract class ExcelExportBase extends ExportBase {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelExportBase.class);

	private int currentIndex = 0;

	protected ExcelType type = ExcelType.HSSF;

	private Map<Integer, Double> statistics = new HashMap<Integer, Double>();

	private static final DecimalFormat DOUBLE_FORMAT = new DecimalFormat("######0.00");

	// 修改成protected，列循环时继承类需要用到 ---
	protected IExcelExportStyler excelExportStyler;

	/**
	 * 创建 最主要的 Cells
	 * 
	 * @param styles
	 * @param rowHeight
	 * @throws Exception
	 */
	public int createCells(Drawing patriarch, int index, Object t, List<ExcelExportEntity> excelParams, Sheet sheet, Workbook workbook, short rowHeight) throws Exception {
		ExcelExportEntity entity;
		Row row = sheet.createRow(index);
		row.setHeight(rowHeight);
		int maxHeight = 1, cellNum = 0;
		int indexKey = createIndexCell(row, index, excelParams.get(0));
		cellNum += indexKey;
		for (int k = indexKey, paramSize = excelParams.size(); k < paramSize; k++) {
			entity = excelParams.get(k);
			// 建议autoPoi升级，优化数据返回List Map格式下的复合表头导出excel的体验
			if(entity.isSubColumn()){
				continue;
			}
			if(entity.isMergeColumn()){
				Map<String,Object> subColumnMap = new HashMap<>();
				List<String> mapKeys = entity.getSubColumnList();
				for (String subKey : mapKeys) {
					Object subKeyValue = null;
					if (t instanceof Map) {
						subKeyValue = ((Map<?, ?>) t).get(subKey);
					}else{
						subKeyValue = PoiPublicUtil.getParamsValue(subKey,t);
					}
					subColumnMap.put(subKey,subKeyValue);
				}
				createListCells(patriarch, index, cellNum, subColumnMap, entity.getList(), sheet, workbook);
				cellNum += entity.getSubColumnList().size();
			} else if (entity.getList() != null) {
				Collection<?> list = getListCellValue(entity, t);
				int listC = 0;
				for (Object obj : list) {
					createListCells(patriarch, index + listC, cellNum, obj, entity.getList(), sheet, workbook);
					listC++;
				}
				cellNum += entity.getList().size();
				if (list != null && list.size() > maxHeight) {
					maxHeight = list.size();
				}
			} else {
				Object value = getCellValue(entity, t);
				// AutoPoi 导出类型，type增加数字类型
				if (entity.getType() == 1) {
					createStringCell(row, cellNum++, value == null ? "" : value.toString(), index % 2 == 0 ? getStyles(false, entity) : getStyles(true, entity), entity);
				} else if (entity.getType() == 4){
					createNumericCell(row, cellNum++, value == null ? "" : value.toString(), index % 2 == 0 ? getStyles(false, entity) : getStyles(true, entity), entity);
				} else {
					createImageCell(patriarch, entity, row, cellNum++, value == null ? "" : value.toString(), t);
				}
				// @Excel注解中不支持超链接，但文档中支持
				if (entity.isHyperlink()) {
					row.getCell(cellNum - 1)
							.setHyperlink(dataHanlder.getHyperlink(
									row.getSheet().getWorkbook().getCreationHelper(), t,
									entity.getName(), value));
				}
			}
		}
		// 合并需要合并的单元格
		cellNum = 0;
		for (int k = indexKey, paramSize = excelParams.size(); k < paramSize; k++) {
			entity = excelParams.get(k);
			if (entity.getList() != null) {
				cellNum += entity.getList().size();
			} else if (entity.isNeedMerge()) {
				for (int i = index + 1; i < index + maxHeight; i++) {
					sheet.getRow(i).createCell(cellNum);
					sheet.getRow(i).getCell(cellNum).setCellStyle(getStyles(false, entity));
				}
				// 一对多导出needMerge 子表数据对应数量小于2时报错
				try {
				sheet.addMergedRegion(new CellRangeAddress(index, index + maxHeight - 1, cellNum, cellNum));
				}catch (IllegalArgumentException e){
					LOGGER.error("合并单元格错误日志："+e.getMessage());
					e.fillInStackTrace();
				}
				cellNum++;
			}
		}
		return maxHeight;

	}

	/**
	 * 通过https地址获取图片数据
	 * @param imagePath
	 * @return
	 * @throws Exception
	 */
	private byte[] getImageDataByHttps(String imagePath) throws Exception {
		SSLContext sslcontext = SSLContext.getInstance("SSL","SunJSSE");
		sslcontext.init(null, new TrustManager[]{new MyX509TrustManager()}, new SecureRandom());
		URL url = new URL(imagePath);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setSSLSocketFactory(sslcontext.getSocketFactory());
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5 * 1000);
		InputStream inStream = conn.getInputStream();
		byte[] value = readInputStream(inStream);
		return value;
	}

	/**
	 * 通过http地址获取图片数据
	 * @param imagePath
	 * @return
	 * @throws Exception
	 */
	private byte[] getImageDataByHttp(String imagePath) throws Exception {
		URL url = new URL(imagePath);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5 * 1000);
		InputStream inStream = conn.getInputStream();
		byte[] value = readInputStream(inStream);
		return value;
	}

	/**
	 * 图片类型的Cell
	 * 
	 * @param patriarch
	 * @param entity
	 * @param row
	 * @param i
	 * @param imagePath
	 * @param obj
	 * @throws Exception
	 */
	public void createImageCell(Drawing patriarch, ExcelExportEntity entity, Row row, int i, String imagePath, Object obj) throws Exception {
		row.setHeight((short) (50 * entity.getHeight()));
		row.createCell(i);
		ClientAnchor anchor;
		if (type.equals(ExcelType.HSSF)) {
			anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) i, row.getRowNum(), (short) (i + 1), row.getRowNum() + 1);
		} else {
			anchor = new XSSFClientAnchor(0, 0, 0, 0, (short) i, row.getRowNum(), (short) (i + 1), row.getRowNum() + 1);
		}

		if (StringUtils.isEmpty(imagePath)) {
			return;
		}

		// 【多任务】online 专项集中问题
		int imageType = entity.getExportImageType();
		byte[] value = null;
		if(imageType == 2){
			//原来逻辑 2
			value = (byte[]) (entity.getMethods() != null ? getFieldBySomeMethod(entity.getMethods(), obj) : entity.getMethod().invoke(obj, new Object[] {}));
		} else if(imageType==4 || imagePath.startsWith("http")){
			//新增逻辑 网络图片4
			try {
				if (imagePath.indexOf(",") != -1) {
					if(imagePath.startsWith(",")){
						imagePath = imagePath.substring(1);
					}
					String[] images = imagePath.split(",");
					imagePath = images[0];
				}
				if(imagePath.startsWith("https")){
					value = getImageDataByHttps(imagePath);
				}else{
					value = getImageDataByHttp(imagePath);
				}
			} catch (Exception exception) {
				LOGGER.warn(exception.getMessage());
				//exception.printStackTrace();
			}
		} else {
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			BufferedImage bufferImg;
			String path = null;
			if(imageType == 1){
				//原来逻辑 1
				path = PoiPublicUtil.getWebRootPath(imagePath);
				LOGGER.debug("--- createImageCell getWebRootPath ----filePath--- "+ path);
				path = path.replace("WEB-INF/classes/", "");
				path = path.replace("file:/", "");
			}else if(imageType==3){
				//新增逻辑 本地图片3
				// 本地图片ImageBasePath为空报错的问题
				if(StringUtils.isNotBlank(entity.getImageBasePath())){
					if(!entity.getImageBasePath().endsWith(File.separator) && !imagePath.startsWith(File.separator)){
						path = entity.getImageBasePath()+File.separator+imagePath;
					}else{
						path = entity.getImageBasePath()+imagePath;
					}
				}else{
					path = imagePath;
				}
			}
			try {
				bufferImg = ImageIO.read(new File(path));
				// Excel 导出图片的文件带小数点符号 导出报错
				ImageIO.write(bufferImg, imagePath.substring(imagePath.lastIndexOf(".") + 1, imagePath.length()), byteArrayOut);
				value = byteArrayOut.toByteArray();
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		if (value != null) {
			patriarch.createPicture(anchor, row.getSheet().getWorkbook().addPicture(value, getImageType(value)));
		}


	}

	/**
	 * inStream读取到字节数组
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	private byte[] readInputStream(InputStream inStream) throws Exception {
		if(inStream==null){
			return null;
		}
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		//每次读取的字符串长度，如果为-1，代表全部读取完毕
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}

	private int createIndexCell(Row row, int index, ExcelExportEntity excelExportEntity) {
		if (excelExportEntity.getName().equals("序号") && PoiBaseConstants.IS_ADD_INDEX.equals(excelExportEntity.getFormat())) {
			createStringCell(row, 0, currentIndex + "", index % 2 == 0 ? getStyles(false, null) : getStyles(true, null), null);
			currentIndex = currentIndex + 1;
			return 1;
		}
		return 0;
	}

	/**
	 * 创建List之后的各个Cells
	 * @param patriarch
	 * @param index
	 * @param cellNum
	 * @param obj
	 * @param excelParams
	 * @param sheet
	 * @param workbook
	 * @throws Exception
	 */
	public void createListCells(Drawing patriarch, int index, int cellNum, Object obj, List<ExcelExportEntity> excelParams, Sheet sheet, Workbook workbook) throws Exception {
		ExcelExportEntity entity;
		Row row;
		if (sheet.getRow(index) == null) {
			row = sheet.createRow(index);
			row.setHeight(getRowHeight(excelParams));
		} else {
			row = sheet.getRow(index);
		}
		for (int k = 0, paramSize = excelParams.size(); k < paramSize; k++) {
			entity = excelParams.get(k);
			Object value = getCellValue(entity, obj);
			// AutoPoi 导出类型，type增加数字类型--------------------
			if (entity.getType() == 1) {
				createStringCell(row, cellNum++, value == null ? "" : value.toString(), row.getRowNum() % 2 == 0 ? getStyles(false, entity) : getStyles(true, entity), entity);
				// 新增isHyperlink属性 ---
				if (entity.isHyperlink()) {
					row.getCell(cellNum - 1)
							.setHyperlink(dataHanlder.getHyperlink(
									row.getSheet().getWorkbook().getCreationHelper(), obj, entity.getName(),
									value));
				}
			} else if (entity.getType() == 4){
				createNumericCell(row, cellNum++, value == null ? "" : value.toString(), index % 2 == 0 ? getStyles(false, entity) : getStyles(true, entity), entity);
				// 新增isHyperlink属性 ---
				if (entity.isHyperlink()) {
					row.getCell(cellNum - 1)
							.setHyperlink(dataHanlder.getHyperlink(
									row.getSheet().getWorkbook().getCreationHelper(), obj, entity.getName(),
									value));
				}
			}  else{
				createImageCell(patriarch, entity, row, cellNum++, value == null ? "" : value.toString(), obj);
			}
		}
	}

	// AutoPoi 导出类型，type增加数字类型--------------------
	public void createNumericCell (Row row, int index, String text, CellStyle style, ExcelExportEntity entity) {
		Cell cell = row.createCell(index);	
		if(StringUtils.isEmpty(text)){
			cell.setCellValue("");
			cell.setCellType(CellType.BLANK);
		}else{
			cell.setCellValue(Double.parseDouble(text));
			cell.setCellType(CellType.NUMERIC);
		}
		if (style != null) {
			cell.setCellStyle(style);
		}
		addStatisticsData(index, text, entity);
	}
	
	/**
	 * 创建文本类型的Cell
	 * 
	 * @param row
	 * @param index
	 * @param text
	 * @param style
	 * @param entity
	 */
	public void createStringCell(Row row, int index, String text, CellStyle style, ExcelExportEntity entity) {
		Cell cell = row.createCell(index);
		if (style != null && style.getDataFormat() > 0 && style.getDataFormat() < 12) {
			cell.setCellValue(Double.parseDouble(text));
			cell.setCellType(CellType.NUMERIC);
		}else{
			RichTextString Rtext;
			if (type.equals(ExcelType.HSSF)) {
				Rtext = new HSSFRichTextString(text);
			} else {
				Rtext = new XSSFRichTextString(text);
			}
			cell.setCellValue(Rtext);
		}
		if (style != null) {
			cell.setCellStyle(style);
		}
		addStatisticsData(index, text, entity);
	}

	/**
	 * 设置字段下划线
	 * @param row
	 * @param index
	 * @param text
	 * @param style
	 * @param entity
	 * @param workbook
	 */
	/*public void createStringCell(Row row, int index, String text, CellStyle style, ExcelExportEntity entity, Workbook workbook) {
		Cell cell = row.createCell(index);
		if (style != null && style.getDataFormat() > 0 && style.getDataFormat() < 12) {
			cell.setCellValue(Double.parseDouble(text));
			cell.setCellType(CellType.NUMERIC);
		}else{
			RichTextString Rtext;
			if (type.equals(ExcelType.HSSF)) {
				Rtext = new HSSFRichTextString(text);
			} else {
				Rtext = new XSSFRichTextString(text);
			}
			cell.setCellValue(Rtext);
		}
		if (style != null) {
			Font font = workbook.createFont();
			font.setUnderline(Font.U_SINGLE);
			style.setFont(font);
			cell.setCellStyle(style);
		}
		addStatisticsData(index, text, entity);
	}*/

	/**
	 * 创建统计行
	 * 
	 * @param styles
	 * @param sheet
	 */
	public void addStatisticsRow(CellStyle styles, Sheet sheet) {
		if (statistics.size() > 0) {
			Row row = sheet.createRow(sheet.getLastRowNum() + 1);
			Set<Integer> keys = statistics.keySet();
			createStringCell(row, 0, "合计", styles, null);
			for (Integer key : keys) {
				createStringCell(row, key, DOUBLE_FORMAT.format(statistics.get(key)), styles, null);
			}
			statistics.clear();
		}

	}

	/**
	 * 合计统计信息
	 * 
	 * @param index
	 * @param text
	 * @param entity
	 */
	private void addStatisticsData(Integer index, String text, ExcelExportEntity entity) {
		if (entity != null && entity.isStatistics()) {
			Double temp = 0D;
			if (!statistics.containsKey(index)) {
				statistics.put(index, temp);
			}
			try {
				temp = Double.valueOf(text);
			} catch (NumberFormatException e) {
			}
			statistics.put(index, statistics.get(index) + temp);
		}
	}

	/**
	 * 获取导出报表的字段总长度
	 * 
	 * @param excelParams
	 * @return
	 */
	public int getFieldWidth(List<ExcelExportEntity> excelParams) {
		int length = -1;// 从0开始计算单元格的
		for (ExcelExportEntity entity : excelParams) {
			// AutoPoi多表头导出，会多出一列空白列
			if(entity.getGroupName()!=null){
				continue;
			}else if (entity.getSubColumnList()!=null&&entity.getSubColumnList().size()>0){
				length += entity.getSubColumnList().size();
			}else{
				length += entity.getList() != null ? entity.getList().size() : 1;
			}
		}
		return length;
	}

	/**
	 * 获取图片类型,设置图片插入类型
	 * 
	 * @param value
	 * @return
	 */
	public int getImageType(byte[] value) {
		String type = PoiPublicUtil.getFileExtendName(value);
		if (type.equalsIgnoreCase("JPG")) {
			return Workbook.PICTURE_TYPE_JPEG;
		} else if (type.equalsIgnoreCase("PNG")) {
			return Workbook.PICTURE_TYPE_PNG;
		}
		return Workbook.PICTURE_TYPE_JPEG;
	}

	private Map<Integer, int[]> getMergeDataMap(List<ExcelExportEntity> excelParams) {
		Map<Integer, int[]> mergeMap = new HashMap<Integer, int[]>();
		// 设置参数顺序,为之后合并单元格做准备
		int i = 0;
		for (ExcelExportEntity entity : excelParams) {
			if (entity.isMergeVertical()) {
				mergeMap.put(i, entity.getMergeRely());
			}
			if (entity.getList() != null) {
				for (ExcelExportEntity inner : entity.getList()) {
					if (inner.isMergeVertical()) {
						mergeMap.put(i, inner.getMergeRely());
					}
					i++;
				}
			} else {
				i++;
			}
		}
		return mergeMap;
	}

	/**
	 * 获取样式
	 * 
	 * @param entity
	 * @param needOne
	 * @return
	 */
	public CellStyle getStyles(boolean needOne, ExcelExportEntity entity) {
		return excelExportStyler.getStyles(needOne, entity);
	}

	/**
	 * 合并单元格
	 * 
	 * @param sheet
	 * @param excelParams
	 * @param titleHeight
	 */
	public void mergeCells(Sheet sheet, List<ExcelExportEntity> excelParams, int titleHeight) {
		Map<Integer, int[]> mergeMap = getMergeDataMap(excelParams);
		PoiMergeCellUtil.mergeCells(sheet, mergeMap, titleHeight);
	}

	public void setCellWith(List<ExcelExportEntity> excelParams, Sheet sheet) {
		int index = 0;
		for (int i = 0; i < excelParams.size(); i++) {
			if (excelParams.get(i).getList() != null) {
				List<ExcelExportEntity> list = excelParams.get(i).getList();
				for (int j = 0; j < list.size(); j++) {
					sheet.setColumnWidth(index, (int) (256 * list.get(j).getWidth()));
					index++;
				}
			} else {
				sheet.setColumnWidth(index, (int) (256 * excelParams.get(i).getWidth()));
				index++;
			}
		}
	}

	/**
	 * 设置隐藏列
	 * @param excelParams
	 * @param sheet
	 */
	public void setColumnHidden(List<ExcelExportEntity> excelParams, Sheet sheet) {
		int index = 0;
		for (int i = 0; i < excelParams.size(); i++) {
			if (excelParams.get(i).getList() != null) {
				List<ExcelExportEntity> list = excelParams.get(i).getList();
				for (int j = 0; j < list.size(); j++) {
					sheet.setColumnHidden(index, list.get(j).isColumnHidden());
					index++;
				}
			} else {
				sheet.setColumnHidden(index, excelParams.get(i).isColumnHidden());
				index++;
			}
		}
	}
	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	public void setExcelExportStyler(IExcelExportStyler excelExportStyler) {
		this.excelExportStyler = excelExportStyler;
	}

	public IExcelExportStyler getExcelExportStyler() {
		return excelExportStyler;
	}
	//  【autopoi】大数据导出方法【全局】----
    /**
     *创建单元格，返回最大高度和单元格数
     * @param patriarch
     * @param index
     * @param t
     * @param excelParams
     * @param sheet
     * @param workbook
     * @param rowHeight 行高
     * @param cellNum 格数
     * @return
     */
	public int[] createCells(Drawing patriarch, int index, Object t,
							 List<ExcelExportEntity> excelParams, Sheet sheet, Workbook workbook,
							 short rowHeight, int cellNum) {
		try {
			ExcelExportEntity entity;
			Row               row = sheet.getRow(index) == null ? sheet.createRow(index) : sheet.getRow(index);
			if (rowHeight != -1) {
				row.setHeight(rowHeight);
			}
			int maxHeight = 1, listMaxHeight = 1;
			// 合并需要合并的单元格
			int margeCellNum = cellNum;
			int indexKey     = 0;
			if (excelParams != null && !excelParams.isEmpty()) {
				indexKey = createIndexCell(row, index, excelParams.get(0));
			}
			cellNum += indexKey;
			for (int k = indexKey, paramSize = excelParams.size(); k < paramSize; k++) {
				entity = excelParams.get(k);
				//不论数据是否为空都应该把该列的数据跳过去
				if (entity.getList() != null) {
					Collection<?> list          = getListCellValue(entity, t);
					int           tmpListHeight = 0;
					if (list != null && list.size() > 0) {
						int tempCellNum = 0;
						for (Object obj : list) {
							int[] temp = createCells(patriarch, index + tmpListHeight, obj, entity.getList(), sheet, workbook, rowHeight, cellNum);
							tempCellNum = temp[1];
							tmpListHeight += temp[0];
						}
						cellNum = tempCellNum;
						listMaxHeight = Math.max(listMaxHeight, tmpListHeight);
					} else {
						cellNum = cellNum + getListCellSize(entity.getList());
					}
				} else {
					Object value = getCellValue(entity, t);
					if (entity.getType() == 1) {
						createStringCell(row, cellNum++, value == null ? "" : value.toString(),
								index % 2 == 0 ? getStyles(false, entity) : getStyles(true, entity),
								entity);

					} else if (entity.getType() == 4) {
						createNumericCell(row, cellNum++, value == null ? "" : value.toString(),
								index % 2 == 0 ? getStyles(false, entity) : getStyles(true, entity),
								entity);
					} else {
						createImageCell(patriarch, entity, row, cellNum++,
								value == null ? "" : value.toString(), t);
					}
					// 新增isHyperlink属性 ---
					if (entity.isHyperlink()) {
						row.getCell(cellNum - 1)
								.setHyperlink(dataHanlder.getHyperlink(
										row.getSheet().getWorkbook().getCreationHelper(), t,
										entity.getName(), value));
					}
				}
			}
			maxHeight += listMaxHeight - 1;
			if (indexKey == 1 && excelParams.get(1).isNeedMerge()) {
				excelParams.get(0).setNeedMerge(true);
			}
			for (int k = indexKey, paramSize = excelParams.size(); k < paramSize; k++) {
				entity = excelParams.get(k);
				if (entity.getList() != null) {
					margeCellNum += entity.getList().size();
				} else if (entity.isNeedMerge() && maxHeight > 1) {
					for (int i = index + 1; i < index + maxHeight; i++) {
						if (sheet.getRow(i) == null) {
							sheet.createRow(i);
						}
						sheet.getRow(i).createCell(margeCellNum);
						sheet.getRow(i).getCell(margeCellNum).setCellStyle(getStyles(false, entity));
					}
					PoiMergeCellUtil.addMergedRegion(sheet, index, index + maxHeight - 1, margeCellNum, margeCellNum);
					margeCellNum++;
				}
			}
			return new int[]{maxHeight, cellNum};
		} catch (Exception e) {
			LOGGER.error("excel cell export error ,data is :{}", ReflectionToStringBuilder.toString(t));
			LOGGER.error(e.getMessage(), e);
			throw new ExcelExportException(ExcelExportEnum.EXPORT_ERROR, e);
		}
	}

	/**
	 * 获取集合的宽度
	 *
	 * @param list
	 * @return
	 */
	protected int getListCellSize(List<ExcelExportEntity> list) {
		int cellSize = 0;
		for (ExcelExportEntity ee : list) {
			if (ee.getList() != null) {
				cellSize += getListCellSize(ee.getList());
			} else {
				cellSize++;
			}
		}
		return cellSize;
	}
}
