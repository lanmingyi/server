package com.brightcomplete.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.brightcomplete.common.api.vo.Result;
import com.brightcomplete.modules.system.entity.SysDataSource;

/**
 * @Description: 多数据源管理
 * @Author: lmy
 * @Date: 2019-12-25
 * @Version: V1.0
 */
public interface ISysDataSourceService extends IService<SysDataSource> {

    /**
     * 添加数据源
     * @param sysDataSource
     * @return
     */
    Result saveDataSource(SysDataSource sysDataSource);

    /**
     * 修改数据源
     * @param sysDataSource
     * @return
     */
    Result editDataSource(SysDataSource sysDataSource);


    /**
     * 删除数据源
     * @param id
     * @return
     */
    Result deleteDataSource(String id);
}