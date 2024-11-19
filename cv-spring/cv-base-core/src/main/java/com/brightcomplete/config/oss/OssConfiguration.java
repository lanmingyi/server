package com.brightcomplete.config.oss;

import com.brightcomplete.common.util.oss.OssBootUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 云存储 配置
 * @author lmy
 */
@Configuration
public class OssConfiguration {

    @Value("${brightcomplete.oss.endpoint}")
    private String endpoint;
    @Value("${brightcomplete.oss.accessKey}")
    private String accessKeyId;
    @Value("${brightcomplete.oss.secretKey}")
    private String accessKeySecret;
    @Value("${brightcomplete.oss.bucketName}")
    private String bucketName;
    @Value("${brightcomplete.oss.staticDomain:}")
    private String staticDomain;


    @Bean
    public void initOssBootConfiguration() {
        OssBootUtil.setEndPoint(endpoint);
        OssBootUtil.setAccessKeyId(accessKeyId);
        OssBootUtil.setAccessKeySecret(accessKeySecret);
        OssBootUtil.setBucketName(bucketName);
        OssBootUtil.setStaticDomain(staticDomain);
    }
}