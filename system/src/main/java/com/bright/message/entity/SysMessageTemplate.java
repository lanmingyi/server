package com.bright.message.entity;

import com.bright.common.system.base.entity.BaseEntity;
import com.bright.autopoi.excel.annotation.Excel;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 消息模板
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_sms_template")
public class SysMessageTemplate extends BaseEntity{
	/**模板CODE*/
	@Excel(name = "模板CODE", width = 15)
	private String templateCode;
	/**模板标题*/
	@Excel(name = "模板标题", width = 30)
	private String templateName;
	/**模板内容*/
	@Excel(name = "模板内容", width = 50)
	private String templateContent;
	/**模板测试json*/
	@Excel(name = "模板测试json", width = 15)
	private String templateTestJson;
	/**模板类型*/
	@Excel(name = "模板类型", width = 15)
	private String templateType;

	/**已经应用/未应用  1是0否*/
	@Excel(name = "应用状态", width = 15)
	private String useStatus;

}
