package com.brightcomplete.modules.system.model;

import java.io.Serializable;

import javax.persistence.Table;

/**
 * 移动端代码生成分类表
 *
 * @Author lmy
 * @Date 2022-11-18
 */

public class SysAppClassificationModel implements Serializable {
    private static final long serialVersionUID=1L;

    // 分类名称
    private String classificationName;
    // 分类编码
    private String classificationCode;
    // 分类排序
    private String classificationSort;

    private String uuid;
    private String createTime;
    private String creator;
    private String creatorId;
    private String creatorOrgId;
    private String modifierId;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorOrgId() {
        return creatorOrgId;
    }

    public void setCreatorOrgId(String creatorOrgId) {
        this.creatorOrgId = creatorOrgId;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    private String modifier;
    private String modifyTime;



    /**
     * 设置：分类名称
     */
    public void setClassificationName(String classificationName){
        this.classificationName = classificationName;
    }

    /**
     * 获取：分类名称
     */
    public String getClassificationName(){
        return classificationName;
    }



    /**
     * 设置：分类编码
     */
    public void setClassificationCode(String classificationCode){
        this.classificationCode = classificationCode;
    }

    /**
     * 获取：分类编码
     */
    public String getClassificationCode(){
        return classificationCode;
    }



    /**
     * 设置：分类排序
     */
    public void setClassificationSort(String classificationSort){
        this.classificationSort = classificationSort;
    }

    /**
     * 获取：分类排序
     */
    public String getClassificationSort(){
        return classificationSort;
    }



}
