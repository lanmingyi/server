package com.brightcomplete.config.oss;

import lombok.extern.slf4j.Slf4j;
import com.brightcomplete.common.constant.CommonConstant;
import com.brightcomplete.common.constant.SymbolConstant;
import com.brightcomplete.common.util.MinioUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Minio文件上传配置文件
 * @author lmy
 */
@Slf4j
@Configuration
public class MinioConfig {
    @Value(value = "${brightcomplete.minio.minio_url}")
    private String minioUrl;
    @Value(value = "${brightcomplete.minio.minio_name}")
    private String minioName;
    @Value(value = "${brightcomplete.minio.minio_pass}")
    private String minioPass;
    @Value(value = "${brightcomplete.minio.bucketName}")
    private String bucketName;

    @Bean
    public void initMinio(){
        if(!minioUrl.startsWith(CommonConstant.STR_HTTP)){
            minioUrl = "http://" + minioUrl;
        }
        if(!minioUrl.endsWith(SymbolConstant.SINGLE_SLASH)){
            minioUrl = minioUrl.concat(SymbolConstant.SINGLE_SLASH);
        }
        MinioUtil.setMinioUrl(minioUrl);
        MinioUtil.setMinioName(minioName);
        MinioUtil.setMinioPass(minioPass);
        MinioUtil.setBucketName(bucketName);
    }

}
