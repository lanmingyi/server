package com.bright.system.vo;

import java.util.List;

/**
 * @MethodName
 * @Description TODO  app菜单
 * @Param null
 * @Return
 * @Author lmy
 * @Date 2021-6-1 14:05
 */
public class AppMenuVo {
    // id
    private Integer id;
    // pid
    private Integer pid;
    //图标
    private String cuIcon;
    //演示
    private String color;
    //名称
    private String name;
    //地址
    private String url;
    //排序
    private String sort;
    private List<AppMenuVo> list;

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public List<AppMenuVo> getList() {
        return list;
    }

    public void setList(List<AppMenuVo> list) {
        this.list = list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getCuIcon() {
        return cuIcon;
    }

    public void setCuIcon(String cuIcon) {
        this.cuIcon = cuIcon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
