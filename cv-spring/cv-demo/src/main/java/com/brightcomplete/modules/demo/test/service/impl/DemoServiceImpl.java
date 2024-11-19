package com.brightcomplete.modules.demo.test.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brightcomplete.modules.demo.test.entity.Demo;
import org.apache.shiro.SecurityUtils;
import com.brightcomplete.common.constant.CacheConstant;
import com.brightcomplete.common.system.query.QueryGenerator;
import com.brightcomplete.common.system.vo.LoginUser;
import com.brightcomplete.modules.demo.test.mapper.DemoMapper;
import com.brightcomplete.modules.demo.test.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 测试demo
 * @Author: lmy
 * @Date:  2018-12-29
 * @Version: V1.0
 */
@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements IDemoService {
	@Autowired
	DemoMapper demoMapper;
	
	/**
	 * 事务控制在service层面
	 * 加上注解：@Transactional，声明的方法就是一个独立的事务（有异常DB操作全部回滚）
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void testTran() {
		Demo pp = new Demo();
		pp.setAge(1111);
		pp.setName("测试事务  小白兔 1");
		demoMapper.insert(pp);
		
		Demo pp2 = new Demo();
		pp2.setAge(2222);
		pp2.setName("测试事务  小白兔 2");
		demoMapper.insert(pp2);
        //自定义异常
		Integer.parseInt("hello");
		
		Demo pp3 = new Demo();
		pp3.setAge(3333);
		pp3.setName("测试事务  小白兔 3");
		demoMapper.insert(pp3);
		return ;
	}


	/**
	 * 缓存注解测试： redis
	 */
	@Override
	@Cacheable(cacheNames = CacheConstant.TEST_DEMO_CACHE, key = "#id")
	public Demo getByIdCacheable(String id) {
		Demo t = demoMapper.selectById(id);
		System.err.println("---未读缓存，读取数据库---");
		System.err.println(t);
		return t;
	}


	@Override
	public IPage<Demo> queryListWithPermission(int pageSize, int pageNo) {
		Page<Demo> page = new Page<>(pageNo, pageSize);
		//编程方式，获取当前请求的数据权限规则SQL片段
		String sql = QueryGenerator.installAuthJdbc(Demo.class);
		return this.baseMapper.queryListWithPermission(page, sql);
	}

	@Override
	public String getExportFields() {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		//权限配置列导出示例
		//1.配置前缀与菜单中配置的列前缀一致
		List<String> noAuthList = new ArrayList<>();
		List<String> exportFieldsList = new ArrayList<>();
		String permsPrefix = "testdemo:";
		//查询配置菜单有效字段
		List<String> allAuth = this.demoMapper.queryAllAuth(permsPrefix);
		//查询已授权字段
		List<String> userAuth = this.demoMapper.queryUserAuth(sysUser.getId(),permsPrefix);
		//列出未授权字段
		for(String perms : allAuth){
			if(!userAuth.contains(perms)){
				noAuthList.add(perms.substring(permsPrefix.length()));
			}
		}
		//实体类中字段与未授权字段比较，列出需导出字段
		Field[] fileds = Demo.class.getDeclaredFields();
		List<Field> list = new ArrayList(Arrays.asList(fileds));
		for(Field field : list){
			if(!noAuthList.contains(field.getName())){
				exportFieldsList.add(field.getName());
			}
		}
		return exportFieldsList != null && exportFieldsList.size()>0 ? String.join(",", exportFieldsList) : "";
	}

}
