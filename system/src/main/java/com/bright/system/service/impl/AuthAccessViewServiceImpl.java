package com.bright.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bright.system.mapper.AuthAccessViewMapper;
import com.bright.system.model.*;
import com.bright.system.service.AuthAccessViewService;
import com.bright.system.vo.AppMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unchecked"})
@Service("authAccessViewServiceImpl")
@CacheConfig(cacheNames = "authAccessView")
public class AuthAccessViewServiceImpl extends ServiceImpl<AuthAccessViewMapper, AuthAccessView> implements AuthAccessViewService {

    @Autowired
    private AuthAccessViewMapper authAccessViewMapper;


    @Override
    public List<Map<String, Object>> getAuthorizedMenu(String roleId, String menuId, String codeSetId, String levelId) {
        List<String> list = new ArrayList<String>();
        String d[] = roleId.split(",");
        for (int i = 0; i < d.length; i++) {
            list.add(d[i]);
        }
        List<Map<String, Object>> authAccessViews = authAccessViewMapper.getListByJdbc(list, codeSetId, levelId, menuId);
        return authAccessViews;
    }

    @Override
//    @Cacheable(key = "targetClass + methodName + #p0 + #p1 + #p2")
    @Cacheable(key = "#roleId+'_'+#p1+ '_'+ #p2")
    public List<Map<String, Object>> getAuthorizedMenu(String roleId, String menuId, String codeSetId) {
        List<String> list = new ArrayList<String>();

        String d[] = roleId.split(",");
        for (int i = 0; i < d.length; i++) {
            list.add(d[i]);
        }
        List<Map<String, Object>> authAccessViews = authAccessViewMapper.getListByRoleIdAndCodeSetIdAndMenuId2(list, codeSetId, menuId);
        return authAccessViews;
    }

    @Override
//    @Cacheable(key = "#p0")
    public List<AuthAccessView> getAuthorizedMenu(String roleId) {
        List<String> list = new ArrayList<String>();
        String d[] = roleId.split(",");
        for (int i = 0; i < d.length; i++) {
            list.add(d[i]);
        }
        List<AuthAccessView> authAccessViews = authAccessViewMapper.getListByRoleId(list);
        return authAccessViews;
    }

    @Override
    public List<Map<String, Object>> getListByRoleIdAndPid(String roleId, String pid) {
        List<String> list = new ArrayList<String>();
        String d[] = roleId.split(",");
        for (int i = 0; i < d.length; i++) {
            list.add(d[i]);
        }
        List<Map<String, Object>> authAccessViews = authAccessViewMapper.getListByRoleIdAndPid(list, pid);
        return authAccessViews;
    }

    @Override
    public List<AuthAccessView> getSystemList(String codeSetId, String levelId, String roleId) throws Exception {
        List<String> list = new ArrayList<String>();
        String d[] = roleId.split(",");
        for (int i = 0; i < d.length; i++) {
            list.add(d[i]);
        }
        List<AuthAccessView> authAccessViews = authAccessViewMapper.getListByRoleIdAndCodeSetIdAndLevelId3(list, codeSetId, levelId);
        return authAccessViews;
    }

    @Override
    public List<AuthAccess> getListByRoleIdAllMenu(String roleId,String roleType) {
        if("menu".equals(roleType)){
            return authAccessViewMapper.getListByRoleIdAllMenuData(roleId,"app");
        }
        return authAccessViewMapper.getListByRoleIdAllMenu(roleId,roleType);
    }

    @Override
    public UserInfo getUserInForAndButtonPermissions(String userNameId) {
        return authAccessViewMapper.getUserInForAndButtonPermissions(userNameId);
    }

    @Override
    public List<Permissions> getPermissions(String roleId) {
        return authAccessViewMapper.getPermissions(roleId.split(","));
    }

    @Override
    public List<ActionEntitySet> getActionEntitySet(String roleId) {
        return authAccessViewMapper.getActionEntitySet(roleId.split(","));
    }

    @Override
    public List<AppMenuVo> getListAppMenuByRoleId(String roleId) {
        return authAccessViewMapper.getListAppMenuByRoleId(roleId.split(","));
    }

    @Override
    public List<AuthAccess> getListByRoleIdAllMenuAndUuid(String roleId, String app, String uuid) {
        return authAccessViewMapper.getListByRoleIdAllMenuAndUuid(roleId,app,uuid);
    }
}
