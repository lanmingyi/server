package com.brightcomplete.modules.system.vo;

import com.brightcomplete.modules.system.entity.SysDictItem;
import lombok.Data;
import com.brightcomplete.poi.excel.annotation.Excel;
import com.brightcomplete.poi.excel.annotation.ExcelCollection;

import java.util.List;

/**
 * @Description: 系统字典分页
 * @author lmy
 */
@Data
public class SysDictPage {

    /**
     * 主键
     */
    private String id;
    /**
     * 字典名称
     */
    @Excel(name = "字典名称", width = 20)
    private String dictName;

    /**
     * 字典编码
     */
    @Excel(name = "字典编码", width = 30)
    private String dictCode;
    /**
     * 删除状态
     */
    private Integer delFlag;
    /**
     * 描述
     */
    @Excel(name = "描述", width = 30)
    private String description;

    @ExcelCollection(name = "字典列表")
    private List<SysDictItem> sysDictItemList;

}
