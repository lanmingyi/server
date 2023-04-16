package com.bright.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bright.common.api.vo.Result;
import com.bright.system.entity.SysDataSource;

/**
 * @Description: 多数据源管理
 */
public interface SysDataSourceService extends IService<SysDataSource> {

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
