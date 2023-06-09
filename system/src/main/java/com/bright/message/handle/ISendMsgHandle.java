package com.bright.message.handle;

import com.bright.common.api.dto.message.MessageDTO;

/**
 * @Description: 发送信息接口
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
