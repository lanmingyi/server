package com.bright.message.service;

import java.util.List;

import com.bright.common.system.base.service.BaseService;
import com.bright.message.entity.SysMessageTemplate;

/**
 * @Description: 消息模板
 */
public interface ISysMessageTemplateService extends BaseService<SysMessageTemplate> {

    /**
     * 通过模板CODE查询消息模板
     * @param code 模板CODE
     * @return
     */
    List<SysMessageTemplate> selectByCode(String code);
}
