package com.bright.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bright.system.entity.SysDepartPermission;
import com.bright.system.entity.SysPermissionDataRule;

import java.util.List;

/**
 * 部门权限表
 */
public interface SysDepartPermissionService extends IService<SysDepartPermission> {
    /**
     * 保存授权 将上次的权限和这次作比较 差异处理提高效率
     * @param departId
     * @param permissionIds
     * @param lastPermissionIds
     */
    public void saveDepartPermission(String departId,String permissionIds,String lastPermissionIds);

    /**
     * 根据部门id，菜单id获取数据规则
     * @param permissionId 菜单id
     * @param departId 部门id
     * @return
     */
    List<SysPermissionDataRule> getPermRuleListByDeptIdAndPermId(String departId,String permissionId);
}
