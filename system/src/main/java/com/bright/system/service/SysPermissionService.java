package com.bright.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bright.common.exception.BaseException;
import com.bright.system.entity.SysPermission;
import com.bright.system.model.TreeModel;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 */
public interface SysPermissionService extends IService<SysPermission> {
	/**
	 * 切换vue3菜单
	 */
	public void switchVue3Menu();
	
    /**
     * 通过父id查询菜单
     * @param parentId 父id
     * @return
     */
	public List<TreeModel> queryListByParentId(String parentId);
	
	/**
     * 真实删除
     * @param id 菜单id
     * @throws BaseException
     */
	public void deletePermission(String id) throws BaseException;
	/**
     * 逻辑删除
     * @param id 菜单id
     * @throws BaseException
     */
	public void deletePermissionLogical(String id) throws BaseException;

    /**
     * 添加菜单
     * @param sysPermission SysPermission对象
     * @throws BaseException
     */
	public void addPermission(SysPermission sysPermission) throws BaseException;

    /**
     * 编辑菜单
     * @param sysPermission SysPermission对象
     * @throws BaseException
     */
	public void editPermission(SysPermission sysPermission) throws BaseException;

    /**
     * 获取登录用户拥有的权限
     * @param username 用户名
     * @return
     */
	public List<SysPermission> queryByUser(String username);
	
	/**
	 * 根据permissionId删除其关联的SysPermissionDataRule表中的数据
	 * 
	 * @param id
	 * @return
	 */
	public void deletePermRuleByPermId(String id);
	
	/**
	  * 查询出带有特殊符号的菜单地址的集合
	 * @return
	 */
	public List<String> queryPermissionUrlWithStar();

	/**
	 * 判断用户否拥有权限
	 * @param username
	 * @param sysPermission
	 * @return
	 */
	public boolean hasPermission(String username, SysPermission sysPermission);

	/**
	 * 根据用户和请求地址判断是否有此权限
	 * @param username
	 * @param url
	 * @return
	 */
	public boolean hasPermission(String username, String url);
}
