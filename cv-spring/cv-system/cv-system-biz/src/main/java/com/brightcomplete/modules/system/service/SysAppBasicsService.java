package com.brightcomplete.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.brightcomplete.modules.system.entity.SysAppBasics;
import com.brightcomplete.modules.system.vo.TaleDetailVo;

import java.util.List;


/**
 * 移动端代码生成基础表
 *
 * @Author 明成
 * @Email
 * @Date 2022-12-03
 */
public interface SysAppBasicsService extends IService<SysAppBasics> {

    //    //分页
//    PageSet<SysAppClassificationModel> getPageSet(PageParam pageParam, String filterSort);
//
//    //删除
//    int executeDeleteBatch(String[] uuid);

    // 根据basicsTableName，basicsTableSchema查询数据集
    List<TaleDetailVo> getColumnName(String basicsTableName, String basicsTableSchema);

}
