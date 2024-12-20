package com.brightcomplete.config.sign.util;

import com.alibaba.fastjson.JSONObject;
import com.brightcomplete.common.constant.SymbolConstant;
import com.brightcomplete.common.util.SpringContextUtils;
import com.brightcomplete.common.util.oConvertUtils;
import com.brightcomplete.config.BaseConfig;
import lombok.extern.slf4j.Slf4j;
import com.brightcomplete.common.exception.BaseException;
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
        BaseConfig baseConfig = SpringContextUtils.getBean(BaseConfig.class);
        String signatureSecret = baseConfig.getSignatureSecret();
        String curlyBracket = SymbolConstant.DOLLAR + SymbolConstant.LEFT_CURLY_BRACKET;
        if(oConvertUtils.isEmpty(signatureSecret) || signatureSecret.contains(curlyBracket)){
            throw new BaseException("签名密钥 ${brightcomplete.signatureSecret} 缺少配置 ！！");
        }
        return DigestUtils.md5DigestAsHex((paramsJsonStr + signatureSecret).getBytes()).toUpperCase();
    }
}