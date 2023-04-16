package com.bright.autopoi.excel;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.bright.autopoi.excel.entity.enmus.ExcelType;
import com.bright.autopoi.excel.entity.params.ExcelExportEntity;
import com.bright.autopoi.excel.export.ExcelBatchExportServer;
import com.bright.autopoi.excel.export.ExcelExportServer;
import com.bright.autopoi.excel.export.template.ExcelExportOfTemplateUtil;
import com.bright.autopoi.handler.inter.IExcelExportServer;
import com.bright.autopoi.handler.inter.IWriter;
import com.bright.autopoi.excel.entity.ExportParams;
import com.bright.autopoi.excel.entity.TemplateExportParams;

/**
 * excel 导出工具类
 */
public final class ExcelExportUtil {
	// 【autopoi】大数据导出方法【全局】
	//单sheet最大值
	public static       int    USE_SXSSF_LIMIT = 100000;
	private ExcelExportUtil() {
	}

	// 导出字段支持自定义
	/**
	 * 根据Entity创建对应的Excel
	 * 
	 * @param entity
	 *            表格标题属性
	 * @param pojoClass
	 *            Excel对象Class
	 * @param dataSet
	 *            Excel对象数据List
	 * @param exportFields
	 * 	          自定义导出Excel字段数组
	 */
	public static Workbook exportExcel(ExportParams entity, Class<?> pojoClass, Collection<?> dataSet,String[] exportFields) {
		Workbook workbook;
		if (ExcelType.HSSF.equals(entity.getType())) {
			workbook = new HSSFWorkbook();
		} else if (dataSet.size() < 1000) {
			workbook = new XSSFWorkbook();
		} else {
			workbook = new SXSSFWorkbook();
		}
		new ExcelExportServer().createSheet(workbook, entity, pojoClass, dataSet,exportFields);
		return workbook;
	}


	/**
	 * 根据Entity创建对应的Excel
	 *
	 * @param entity
	 *            表格标题属性
	 * @param pojoClass
	 *            Excel对象Class
	 * @param dataSet
	 *            Excel对象数据List
	 */
	public static Workbook exportExcel(ExportParams entity, Class<?> pojoClass, Collection<?> dataSet) {
		Workbook workbook;
		if (ExcelType.HSSF.equals(entity.getType())) {
			workbook = new HSSFWorkbook();
		} else if (dataSet.size() < 1000) {
			workbook = new XSSFWorkbook();
		} else {
			workbook = new SXSSFWorkbook();
		}
		new ExcelExportServer().createSheet(workbook, entity, pojoClass, dataSet,null);
		return workbook;
	}

	/**
	 * 根据Map创建对应的Excel
	 * 
	 * @param entity
	 *            表格标题属性
	 * @param pojoClass
	 *            Excel对象Class
	 * @param dataSet
	 *            Excel对象数据List
	 */
	public static Workbook exportExcel(ExportParams entity, List<ExcelExportEntity> entityList, Collection<? extends Map<?, ?>> dataSet) {
		Workbook workbook;
		if (ExcelType.HSSF.equals(entity.getType())) {
			workbook = new HSSFWorkbook();
		} else if (dataSet.size() < 1000) {
			workbook = new XSSFWorkbook();
		} else {
			workbook = new SXSSFWorkbook();
		}
		new ExcelExportServer().createSheetForMap(workbook, entity, entityList, dataSet);
		return workbook;
	}

	/**
	 * 一个excel 创建多个sheet
	 * 
	 * @param list
	 *            多个Map key title 对应表格Title key entity 对应表格对应实体 key data
	 *            Collection 数据
	 * @return
	 */
	public static Workbook exportExcel(List<Map<String, Object>> list, ExcelType type) {
		Workbook workbook;
		if (ExcelType.HSSF.equals(type)) {
			workbook = new HSSFWorkbook();
		} else {
			workbook = new XSSFWorkbook();
		}
		for (Map<String, Object> map : list) {
			ExcelExportServer server = new ExcelExportServer();
			server.createSheet(workbook, (ExportParams) map.get("title"), (Class<?>) map.get("entity"), (Collection<?>) map.get("data"),null);
		}
		return workbook;
	}

	/**
	 * 导出文件通过模板解析,不推荐这个了,推荐全部通过模板来执行处理
	 * 
	 * @param params
	 *            导出参数类
	 * @param pojoClass
	 *            对应实体
	 * @param dataSet
	 *            实体集合
	 * @param map
	 * 
	 *            模板集合
	 * @return
	 */
	public static Workbook exportExcel(TemplateExportParams params, Class<?> pojoClass, Collection<?> dataSet, Map<String, Object> map) {
		return new ExcelExportOfTemplateUtil().createExcleByTemplate(params, pojoClass, dataSet, map);
	}

	/**
	 * 导出文件通过模板解析只有模板,没有集合
	 * 
	 * @param params
	 *            导出参数类
	 * @param map
	 *            模板集合
	 * @return
	 */
	public static Workbook exportExcel(TemplateExportParams params, Map<String, Object> map) {
		return new ExcelExportOfTemplateUtil().createExcleByTemplate(params, null, null, map);
	}


	// 大数据导出方法【全局
	/**
	 * 大数据量导出
	 *
	 * @param entity    表格标题属性
	 * @param pojoClass Excel对象Class
	 * @return ExcelBatchExportServer 批处理服务
	 */
	public static IWriter<Workbook> exportBigExcel(ExportParams entity, Class<?> pojoClass) {
		ExcelBatchExportServer batchServer = new ExcelBatchExportServer();
		batchServer.init(entity, pojoClass);
		return batchServer;
	}

	/**
	 * 大数据量导出
	 *
	 * @param entity
	 * @param excelParams
	 * @return ExcelBatchExportServer 批处理服务
	 */
	public static IWriter<Workbook> exportBigExcel(ExportParams entity, List<ExcelExportEntity> excelParams) {
		ExcelBatchExportServer batchServer = new ExcelBatchExportServer();
		batchServer.init(entity, excelParams);
		return batchServer;
	}

	/**
	 * 大数据量导出
	 *
	 * @param entity      导出参数属性
	 * @param pojoClass   Excel对象Class
	 * @param server      查询数据的接口
	 * @param queryParams 查询数据的参数
	 * @return Workbook
	 */
	public static Workbook exportBigExcel(ExportParams entity, Class<?> pojoClass,
                                          IExcelExportServer server, Object queryParams) {
		ExcelBatchExportServer batchServer = new ExcelBatchExportServer();
		batchServer.init(entity, pojoClass);
		return batchServer.exportBigExcel(server, queryParams);
	}

	/**
	 * 大数据量导出
	 * @param entity
	 * @param excelParams
	 * @param server      查询数据的接口
	 * @param queryParams 查询数据的参数
	 * @return Workbook
	 */
	public static Workbook exportBigExcel(ExportParams entity, List<ExcelExportEntity> excelParams,
										  IExcelExportServer server, Object queryParams) {
		ExcelBatchExportServer batchServer = new ExcelBatchExportServer();
		batchServer.init(entity, excelParams);
		return batchServer.exportBigExcel(server, queryParams);
	}
}
