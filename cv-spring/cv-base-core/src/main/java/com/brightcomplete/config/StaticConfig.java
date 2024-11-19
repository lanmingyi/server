package com.brightcomplete.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 设置静态参数初始化
 * @author lmy
 */
@Component
@Data
public class StaticConfig {

    @Value("${brightcomplete.oss.accessKey}")
    private String accessKeyId;

    @Value("${brightcomplete.oss.secretKey}")
    private String accessKeySecret;

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

//    /**
//     * 签名密钥串
//     */
//    @Value(value = "${brightcomplete.signatureSecret}")
//    private String signatureSecret;


    /*@Bean
    public void initStatic() {
       DySmsHelper.setAccessKeyId(accessKeyId);
       DySmsHelper.setAccessKeySecret(accessKeySecret);
       EmailSendMsgHandle.setEmailFrom(emailFrom);
    }*/

}
