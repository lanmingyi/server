package com.brightcomplete.modules.message.service.impl;

import com.brightcomplete.common.system.base.service.impl.BaseServiceImpl;
import com.brightcomplete.modules.message.entity.SysMessageTemplate;
import com.brightcomplete.modules.message.mapper.SysMessageTemplateMapper;
import com.brightcomplete.modules.message.service.ISysMessageTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Description: 消息模板
 * @Author: lmy
 * @Date:  2019-04-09
 * @Version: V1.0
 */
@Service
public class SysMessageTemplateServiceImpl extends BaseServiceImpl<SysMessageTemplateMapper, SysMessageTemplate> implements ISysMessageTemplateService {

    @Autowired
    private SysMessageTemplateMapper sysMessageTemplateMapper;


    @Override
    public List<SysMessageTemplate> selectByCode(String code) {
        return sysMessageTemplateMapper.selectByCode(code);
    }
}
