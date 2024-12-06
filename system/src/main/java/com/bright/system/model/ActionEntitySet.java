package com.bright.system.model;


/**
 * @MethodName
 * @Description TODO  菜单对应的按钮
 * @Param null
 * @Return
 * @Author lmy
 * @Date 2021-5-18 18:01
 */
public class ActionEntitySet {
    private String action;
    private String defaultCheck;
    private String describe;
    private String roleId;
    private String id;
    private String pid;


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDefaultCheck() {
        if ("".equals(defaultCheck) || defaultCheck == null) {
            return "false";
        }
        return defaultCheck;
    }

    public void setDefaultCheck(String defaultCheck) {
        this.defaultCheck = defaultCheck;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
