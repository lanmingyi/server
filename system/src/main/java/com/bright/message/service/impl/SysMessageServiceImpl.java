package com.bright.message.service.impl;

import com.bright.common.system.base.service.impl.BaseServiceImpl;
import com.bright.message.entity.SysMessage;
import com.bright.message.mapper.SysMessageMapper;
import com.bright.message.service.ISysMessageService;
import org.springframework.stereotype.Service;

/**
 * @Description: 消息
 */
@Service
public class SysMessageServiceImpl extends BaseServiceImpl<SysMessageMapper, SysMessage> implements ISysMessageService {

}
