package com.bright.autopoi.excel.export.styler;

import org.apache.poi.ss.usermodel.CellStyle;
import com.bright.autopoi.excel.entity.params.ExcelExportEntity;
import com.bright.autopoi.excel.entity.params.ExcelForEachParams;

/**
 * Excel导出样式接口
 */
public interface IExcelExportStyler {

	/**
	 * 列表头样式
	 * 
	 * @param headerColor
	 * @return
	 */
	public CellStyle getHeaderStyle(short headerColor);

	/**
	 * 标题样式
	 * 
	 * @param color
	 * @return
	 */
	public CellStyle getTitleStyle(short color);

	/**
	 * 获取样式方法
	 * 
	 * @param noneStyler
	 * @param entity
	 * @return
	 */
	public CellStyle getStyles(boolean noneStyler, ExcelExportEntity entity);
	/**
	 * 模板使用的样式设置
	 */
	public CellStyle getTemplateStyles(boolean isSingle, ExcelForEachParams excelForEachParams);

}
