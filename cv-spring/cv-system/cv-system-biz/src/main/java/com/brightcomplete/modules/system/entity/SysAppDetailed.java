package com.brightcomplete.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

@Data
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@TableName("sys_app_detailed")
public class SysAppDetailed {

    private static final long serialVersionUID = 1L;
    /**id*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "uuid")
    private java.lang.String uuid;

    /**创建人*/
    private java.lang.String creator;
    /**用户id*/
    private java.lang.String creatorId;
    /**用户组织id*/
    private java.lang.String creatorOrgId;
    /**修改人id*/
    private java.lang.String modifierId;
    /**更新人*/
    private java.lang.String modifier;

    /**创建时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.util.Date createTime;

    /**更新时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.util.Date modifyTime;

    // 列表数据
    private String listData;
    // 表单数据
    private String formData;

    private String searchData;

    private String basicsUuid;

    private String codeViewData;
}