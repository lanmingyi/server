package com.brightcomplete.starter.cloud.util;

import com.alibaba.fastjson.JSONObject;
import com.brightcomplete.config.JeecgCloudBaseConfig;
import lombok.extern.slf4j.Slf4j;
import com.brightcomplete.common.exception.CommonException;
import com.brightcomplete.common.util.SpringContextHolder;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import java.util.SortedMap;

/**
 * 签名工具类
 *
 * @author lmy
 * @date 20210621
 */
@Slf4j
public class SignUtil {
    public static final String X_PATH_VARIABLE = "x-path-variable";
    /**
     * 符号：美元 $
     */
    private static final String DOLLAR = "$";
    /**
     * 符号：左花括号 }
     */
    private static final String LEFT_CURLY_BRACKET = "{";

    /**
     * @param params
     *            所有的请求参数都会在这里进行排序加密
     * @return 验证签名结果
     */
    public static boolean verifySign(SortedMap<String, String> params,String headerSign) {
        if (params == null || StringUtils.isEmpty(headerSign)) {
            return false;
        }
        // 把参数加密
        String paramsSign = getParamsSign(params);
        log.info("Param Sign : {}", paramsSign);
        return !StringUtils.isEmpty(paramsSign) && headerSign.equals(paramsSign);
    }

    /**
     * @param params
     *            所有的请求参数都会在这里进行排序加密
     * @return 得到签名
     */
    public static String getParamsSign(SortedMap<String, String> params) {
        //去掉 Url 里的时间戳
        params.remove("_t");
        String paramsJsonStr = JSONObject.toJSONString(params);
        log.info("Param paramsJsonStr : {}", paramsJsonStr);
        //设置签名秘钥
        JeecgCloudBaseConfig baseConfig = SpringContextHolder.getBean(JeecgCloudBaseConfig.class);
        String signatureSecret = baseConfig.getSignatureSecret();
        String curlyBracket = SignUtil.DOLLAR + SignUtil.LEFT_CURLY_BRACKET;
        if(StringUtils.isEmpty(signatureSecret) || signatureSecret.contains(curlyBracket)){
            throw new CommonException("签名密钥 ${brightcomplete.signatureSecret} 缺少配置 ！！");
        }
        return DigestUtils.md5DigestAsHex((paramsJsonStr + signatureSecret).getBytes()).toUpperCase();
    }
}
