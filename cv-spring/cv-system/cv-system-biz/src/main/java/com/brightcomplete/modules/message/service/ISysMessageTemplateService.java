package com.brightcomplete.modules.message.service;

import java.util.List;

import com.brightcomplete.common.system.base.service.BaseService;
import com.brightcomplete.modules.message.entity.SysMessageTemplate;

/**
 * @Description: 消息模板
 * @Author: lmy
 * @Date:  2019-04-09
 * @Version: V1.0
 */
public interface ISysMessageTemplateService extends BaseService<SysMessageTemplate> {

    /**
     * 通过模板CODE查询消息模板
     * @param code 模板CODE
     * @return
     */
    List<SysMessageTemplate> selectByCode(String code);
}
