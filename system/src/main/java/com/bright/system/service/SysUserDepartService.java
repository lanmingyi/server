package com.bright.system.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bright.system.entity.SysUser;
import com.bright.system.entity.SysUserDepart;
import com.bright.system.model.DepartIdModel;

import java.util.List;

/**
 * <p>
 * SysUserDpeart用户组织机构service
 * </p>
 *
 */
public interface SysUserDepartService extends IService<SysUserDepart> {
	

	/**
	 * 根据指定用户id查询部门信息
	 * @param userId
	 * @return
	 */
	List<DepartIdModel> queryDepartIdsOfUser(String userId);
	

	/**
	 * 根据部门id查询用户信息
	 * @param depId
	 * @return
	 */
	List<SysUser> queryUserByDepId(String depId);
  	/**
	 * 根据部门code，查询当前部门和下级部门的用户信息
     * @param depCode 部门code
     * @param realname 真实姓名
     * @return List<SysUser>
	 */
	List<SysUser> queryUserByDepCode(String depCode,String realname);

	/**
	 * 用户组件数据查询
	 * @param departId
	 * @param username
	 * @param pageSize
	 * @param pageNo
     * @param realname
     * @param id
	 * @return
	 */
	IPage<SysUser> queryDepartUserPageList(String departId, String username, String realname, int pageSize, int pageNo,String id);

}
