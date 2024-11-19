package com.brightcomplete.modules.demo.test.service;

import com.brightcomplete.common.system.base.service.BaseService;
import com.brightcomplete.modules.demo.test.entity.Demo;

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @Description: brightcomplete 测试demo
 * @Author: lmy
 * @Date:  2018-12-29
 * @Version: V1.0
 */
public interface IDemoService extends BaseService<Demo> {

    /**
     * 测试事务
     */
	public void testTran();

    /**
     * 通过id过去demo数据，先读缓存，在读数据库
     * @param id 数据库id
     * @return demo对象
     */
	public Demo getByIdCacheable(String id);
	
	/**
	 * 查询列表数据 在service中获取数据权限sql信息
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	IPage<Demo> queryListWithPermission(int pageSize, int pageNo);

	/**
	 * 根据用户权限获取导出字段
	 * @return
	 */
	String getExportFields();
}
