package com.brightcomplete.modules.demo.test.service;

import com.brightcomplete.common.system.base.service.BaseService;
import com.brightcomplete.modules.demo.test.entity.Demo;

import java.util.List;

/**
 * @Description: 动态数据源测试
 * @author lmy
 * @Date:2020-04-21
 */
public interface IDynamicDataService extends BaseService<Demo> {

	/**
	 * 测试从header获取数据源
	 * @return
	 */
	public List<Demo> selectSpelByHeader();

	/**
	 * 使用spel从参数获取
	 * @param dsName
	 * @return
	 */
	public  List<Demo> selectSpelByKey(String dsName);

}
