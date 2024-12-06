package com.bright.system.model;

import java.util.List;


/**
 * @MethodName
 * @Description TODO  菜单
 * @Param null
 * @Return
 * @Author lmy
 * @Date 2021-5-18 18:01
 */
public class Permissions {

    private List<ActionEntitySet> actionEntitySet;
    private String actionList;
    private String actions;
    private String dataAccess;
    private String permissionId;
    private String permissionName;
    private String roleId;
    private String Id;


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public List<ActionEntitySet> getActionEntitySet() {
        return actionEntitySet;
    }

    public void setActionEntitySet(List<ActionEntitySet> actionEntitySet) {
        this.actionEntitySet = actionEntitySet;
    }

    public String getActionList() {
        return actionList;
    }

    public void setActionList(String actionList) {
        this.actionList = actionList;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public String getDataAccess() {
        return dataAccess;
    }

    public void setDataAccess(String dataAccess) {
        this.dataAccess = dataAccess;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
