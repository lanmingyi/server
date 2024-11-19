package com.bright.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bright.system.entity.SysDepartRole;
import com.bright.system.mapper.SysDepartRoleMapper;
import com.bright.system.service.SysDepartRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 部门角色
 * @Author: lmy
 * @Date:   2020-02-12
 * @Version: V1.0
 */
@Service
public class SysDepartRoleServiceImpl extends ServiceImpl<SysDepartRoleMapper, SysDepartRole> implements SysDepartRoleService {

    @Override
    public List<SysDepartRole> queryDeptRoleByDeptAndUser(String orgCode, String userId) {
        return this.baseMapper.queryDeptRoleByDeptAndUser(orgCode,userId);
    }
}
