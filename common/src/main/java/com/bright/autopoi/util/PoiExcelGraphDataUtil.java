package com.bright.autopoi.util;

import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Sheet;
import com.bright.autopoi.excel.graph.entity.ExcelGraph;
import com.bright.autopoi.excel.graph.entity.ExcelGraphElement;

import java.util.List;

/**
 * 构建特殊数据结构
 * 【autopoi】大数据导出方法【全局】
 */
public class PoiExcelGraphDataUtil {
    /**
     * 构建获取数据最后行数  并写入到定义对象中
     * @param dataSourceSheet
     * @param graph
     */
    public static void buildGraphData(Sheet dataSourceSheet, ExcelGraph graph) {
        if (graph != null && graph.getCategory() != null && graph.getValueList() != null
                && graph.getValueList().size() > 0) {
            graph.getCategory().setEndRowNum(dataSourceSheet.getLastRowNum());
            for (ExcelGraphElement e : graph.getValueList()) {
                if (e != null) {
                    e.setEndRowNum(dataSourceSheet.getLastRowNum());
                }
            }
        }
    }

    /**
     * 构建多个图形对象
     * @param dataSourceSheet
     * @param graphList
     */
    public static void buildGraphData(Sheet dataSourceSheet, List<ExcelGraph> graphList) {
        if (graphList != null && graphList.size() > 0) {
            for (ExcelGraph graph : graphList) {
                buildGraphData(dataSourceSheet, graph);
            }
        }
    }

    /**
     * 获取画布,没有就创建一个
     * @param sheet
     * @return
     */
    public static Drawing getDrawingPatriarch(Sheet sheet){
        if(sheet.getDrawingPatriarch() == null){
            sheet.createDrawingPatriarch();
        }
        return sheet.getDrawingPatriarch();
    }
}
