package com.bright.autopoi.excel.graph.entity;

import java.util.List;

/**
 * @Description Excel 图形构造服务
 */
public interface ExcelGraph
{
	public ExcelGraphElement getCategory();
	public List<ExcelGraphElement> getValueList();
	public Integer getGraphType();
	public List<ExcelTitleCell> getTitleCell();
	public List<String> getTitle();
}
