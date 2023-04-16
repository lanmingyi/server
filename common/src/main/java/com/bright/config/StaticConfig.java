package com.bright.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 设置静态参数初始化
 */
@Component
@Data
public class StaticConfig {

    @Value("${bright.oss.accessKey}")
    private String accessKeyId;

    @Value("${bright.oss.secretKey}")
    private String accessKeySecret;

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

//    /**
//     * 签名密钥串
//     */
//    @Value(value = "${bright.signatureSecret}")
//    private String signatureSecret;


    /*@Bean
    public void initStatic() {
       DySmsHelper.setAccessKeyId(accessKeyId);
       DySmsHelper.setAccessKeySecret(accessKeySecret);
       EmailSendMsgHandle.setEmailFrom(emailFrom);
    }*/

}
