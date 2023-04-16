package com.bright.message.handle.impl;

//import com.bright.system.service.impl.ThirdAppWechatEnterpriseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import com.bright.common.api.dto.message.MessageDTO;
import com.bright.message.handle.ISendMsgHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 发企业微信消息模板
 */
@Slf4j
@Component("qywxSendMsgHandle")
public class QywxSendMsgHandle implements ISendMsgHandle {

//	@Autowired
//	private ThirdAppWechatEnterpriseServiceImpl wechatEnterpriseService;

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

//	@Override
//	public void sendMessage(MessageDTO messageDTO) {
//		wechatEnterpriseService.sendMessage(messageDTO, true);
//	}

}
