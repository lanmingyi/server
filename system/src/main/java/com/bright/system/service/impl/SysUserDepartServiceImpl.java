package com.bright.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bright.common.constant.CommonConstant;
import com.bright.common.util.ConvertUtils;
import com.bright.system.entity.SysDepart;
import com.bright.system.entity.SysUser;
import com.bright.system.entity.SysUserDepart;
import com.bright.system.mapper.SysUserDepartMapper;
import com.bright.system.mapper.SysUserMapper;
import com.bright.system.model.DepartIdModel;
import com.bright.system.service.SysDepartService;
import com.bright.system.service.SysUserDepartService;
import com.bright.system.vo.SysUserDepVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <P>
 * 用户部门表实现类
 * <p/>
 */
@Service
public class SysUserDepartServiceImpl extends ServiceImpl<SysUserDepartMapper, SysUserDepart> implements SysUserDepartService {
	@Autowired
	private SysDepartService sysDepartService;
	@Autowired
	private SysUserMapper sysUserMapper;


	/**
	 * 根据用户id查询部门信息
	 */
	@Override
	public List<DepartIdModel> queryDepartIdsOfUser(String userId) {
		LambdaQueryWrapper<SysUserDepart> queryUserDep = new LambdaQueryWrapper<SysUserDepart>();
		LambdaQueryWrapper<SysDepart> queryDep = new LambdaQueryWrapper<SysDepart>();
		try {
            queryUserDep.eq(SysUserDepart::getUserId, userId);
			List<String> depIdList = new ArrayList<>();
			List<DepartIdModel> depIdModelList = new ArrayList<>();
			List<SysUserDepart> userDepList = this.list(queryUserDep);
			if(userDepList != null && userDepList.size() > 0) {
			for(SysUserDepart userDepart : userDepList) {
					depIdList.add(userDepart.getDepId());
				}
			queryDep.in(SysDepart::getId, depIdList);
			List<SysDepart> depList = sysDepartService.list(queryDep);
			//逻辑判断有问题
			if(depList != null && depList.size() > 0) {
				for(SysDepart depart : depList) {
					depIdModelList.add(new DepartIdModel().convertByUserDepart(depart));
				}
			}
			return depIdModelList;
			}
		}catch(Exception e) {
			e.fillInStackTrace();
		}
		return null;


	}


	/**
	 * 根据部门id查询用户信息
	 */
	@Override
	public List<SysUser> queryUserByDepId(String depId) {
		LambdaQueryWrapper<SysUserDepart> queryUserDep = new LambdaQueryWrapper<SysUserDepart>();
		queryUserDep.eq(SysUserDepart::getDepId, depId);
		List<String> userIdList = new ArrayList<>();
		List<SysUserDepart> uDepList = this.list(queryUserDep);
		if(uDepList != null && uDepList.size() > 0) {
			for(SysUserDepart uDep : uDepList) {
				userIdList.add(uDep.getUserId());
			}
			List<SysUser> userList = (List<SysUser>) sysUserMapper.selectBatchIds(userIdList);
			// for:接口调用查询返回结果不能返回密码相关信息
			for (SysUser sysUser : userList) {
				sysUser.setSalt("");
				sysUser.setPassword("");
			}
			return userList;
		}
		return new ArrayList<SysUser>();
	}

	/**
	 * 根据部门code，查询当前部门和下级部门的 用户信息
	 */
	@Override
	public List<SysUser> queryUserByDepCode(String depCode,String realname) {
		// 根据部门选择用户接口代码优化
		if(ConvertUtils.isNotEmpty(realname)){
			realname = realname.trim();
		}
		List<SysUser> userList = this.baseMapper.queryDepartUserList(depCode, realname);
		Map<String, SysUser> map = new HashMap(5);
		for (SysUser sysUser : userList) {
			// 返回的用户数据去掉密码信息
			sysUser.setSalt("");
			sysUser.setPassword("");
			map.put(sysUser.getId(), sysUser);
		}
		return new ArrayList<SysUser>(map.values());

	}

	@Override
	public IPage<SysUser> queryDepartUserPageList(String departId, String username, String realname, int pageSize, int pageNo,String id) {
		IPage<SysUser> pageList = null;
		// 部门ID不存在 直接查询用户表即可
		Page<SysUser> page = new Page<SysUser>(pageNo, pageSize);
		if(ConvertUtils.isEmpty(departId)){
			LambdaQueryWrapper<SysUser> query = new LambdaQueryWrapper<>();
            // 已冻结用户仍可设置为代理人
            query.eq(SysUser::getStatus,Integer.parseInt(CommonConstant.STATUS_1));
            // 已冻结用户仍可设置为代理人
			if(ConvertUtils.isNotEmpty(username)){
				query.like(SysUser::getUsername, username);
			}
            // 邮箱回复时，发送到显示的为用户id
            if(ConvertUtils.isNotEmpty(id)){
                query.eq(SysUser::getId, id);
            }
            // 邮箱回复时，发送到显示的为用户id
			pageList = sysUserMapper.selectPage(page, query);
		}else{
			// 有部门ID 需要走自定义sql
			SysDepart sysDepart = sysDepartService.getById(departId);
			pageList = this.baseMapper.queryDepartUserPageList(page, sysDepart.getOrgCode(), username, realname);
		}
		List<SysUser> userList = pageList.getRecords();
		if(userList!=null && userList.size()>0){
			List<String> userIds = userList.stream().map(SysUser::getId).collect(Collectors.toList());
			Map<String, SysUser> map = new HashMap(5);
			if(userIds!=null && userIds.size()>0){
				// 查部门名称
				Map<String,String>  useDepNames = this.getDepNamesByUserIds(userIds);
				userList.forEach(item->{
					//TODO 临时借用这个字段用于页面展示
					item.setOrgCodeTxt(useDepNames.get(item.getId()));
					item.setSalt("");
					item.setPassword("");
					// 去重
					map.put(item.getId(), item);
				});
			}
			pageList.setRecords(new ArrayList<SysUser>(map.values()));
		}
		return pageList;
	}

	/**
	 * 升级SpringBoot2.6.6,不允许循环依赖
	 * @param userIds
	 * @return
	 */
	private Map<String, String> getDepNamesByUserIds(List<String> userIds) {
		List<SysUserDepVo> list = sysUserMapper.getDepNamesByUserIds(userIds);

		Map<String, String> res = new HashMap(5);
		list.forEach(item -> {
					if (res.get(item.getUserId()) == null) {
						res.put(item.getUserId(), item.getDepartName());
					} else {
						res.put(item.getUserId(), res.get(item.getUserId()) + "," + item.getDepartName());
					}
				}
		);
		return res;
	}

}
