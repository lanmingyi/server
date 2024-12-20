package com.brightcomplete.modules.message.handle;

import com.brightcomplete.common.api.dto.message.MessageDTO;

/**
 * @Description: 发送信息接口
 * @author lmy
 */
public interface ISendMsgHandle {

    /**
     * 发送信息
     * @param esReceiver 发送人
     * @param esTitle 标题
     * @param esContent 内容
     */
	void sendMsg(String esReceiver, String esTitle, String esContent);

    /**
     * 发送信息
     * @param messageDTO
     */
	default void sendMessage(MessageDTO messageDTO){

    }
}
