package com.bright.common.constant;

public interface CommonConstant {

    /**String 类型的空值*/
    String STRING_NULL = "null";

    /**===============================================================================================*/
    /**
     * ::非常重要::
     * 注意：这四个常量值如果修改，需要与 cv-starter/cv-start-cloud/com.brightcomplete.config.FeignConfig 类中的值保持一致。
     */
    String X_ACCESS_TOKEN = "X-Access-Token";
    String X_SIGN = "X-Sign";
    String X_TIMESTAMP = "X-TIMESTAMP";
    /** 租户 请求头*/
    String TENANT_ID = "tenant-id";
    /**===============================================================================================*/

}
