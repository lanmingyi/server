package com.brightcomplete.modules.message.handle.impl;

import com.brightcomplete.modules.system.service.impl.ThirdAppDingtalkServiceImpl;
import lombok.extern.slf4j.Slf4j;
import com.brightcomplete.common.api.dto.message.MessageDTO;
import com.brightcomplete.modules.message.handle.ISendMsgHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 发钉钉消息模板
 * @author lmy
 */
@Slf4j
@Component("ddSendMsgHandle")
public class DdSendMsgHandle implements ISendMsgHandle {

	@Autowired
	private ThirdAppDingtalkServiceImpl dingtalkService;

	@Override
	public void sendMsg(String esReceiver, String esTitle, String esContent) {
		log.info("发微信消息模板");
		MessageDTO messageDTO = new MessageDTO();
		messageDTO.setToUser(esReceiver);
		messageDTO.setTitle(esTitle);
		messageDTO.setContent(esContent);
		messageDTO.setToAll(false);
		sendMessage(messageDTO);
	}

	@Override
	public void sendMessage(MessageDTO messageDTO) {
		dingtalkService.sendMessage(messageDTO, true);
	}

}