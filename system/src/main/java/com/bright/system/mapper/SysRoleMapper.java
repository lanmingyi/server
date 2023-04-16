package com.bright.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bright.system.entity.SysRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 删除角色与用户关系
     * @param roleId
     */
    @Delete("delete from sys_user_role where role_id = #{roleId}")
    void deleteRoleUserRelation(@Param("roleId") String roleId);


    /**
     * 删除角色与权限关系
     * @param roleId
     */
    @Delete("delete from sys_role_permission where role_id = #{roleId}")
    void deleteRolePermissionRelation(@Param("roleId") String roleId);

}
