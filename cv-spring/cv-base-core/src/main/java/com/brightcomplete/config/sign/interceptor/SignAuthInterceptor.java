package com.brightcomplete.config.sign.interceptor;


import java.io.PrintWriter;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brightcomplete.common.api.vo.Result;
import com.brightcomplete.common.util.DateUtils;
import com.brightcomplete.config.sign.util.BodyReaderHttpServletRequestWrapper;
import com.brightcomplete.common.constant.CommonConstant;
import com.brightcomplete.config.sign.util.HttpUtils;
import com.brightcomplete.config.sign.util.SignUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * 签名拦截器
 * @author lmy
 */
@Slf4j
public class SignAuthInterceptor implements HandlerInterceptor {
    /**
     * 5分钟有效期
     */
    private final static long MAX_EXPIRE = 5 * 60;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Sign Interceptor request URI = " + request.getRequestURI());
        HttpServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
        //获取全部参数(包括URL和body上的)
        SortedMap<String, String> allParams = HttpUtils.getAllParams(requestWrapper);
        //对参数进行签名验证
        String headerSign = request.getHeader(CommonConstant.X_SIGN);
        String xTimestamp = request.getHeader(CommonConstant.X_TIMESTAMP);
        //客户端时间
        Long clientTimestamp = Long.parseLong(xTimestamp);

        int length = 14;
        int length1000 = 1000;
        //1.校验签名时间（兼容X_TIMESTAMP的新老格式）
        if (xTimestamp.length() == length) {
            //a. X_TIMESTAMP格式是 yyyyMMddHHmmss (例子：20220308152143)
            if ((DateUtils.getCurrentTimestamp() - clientTimestamp) > MAX_EXPIRE) {
                log.error("签名验证失败:X-TIMESTAMP已过期，注意系统时间和服务器时间是否有误差！");
                throw new IllegalArgumentException("签名验证失败:X-TIMESTAMP已过期");
            }
        } else {
            //b. X_TIMESTAMP格式是 时间戳 (例子：1646552406000)
            if ((System.currentTimeMillis() - clientTimestamp) > (MAX_EXPIRE * length1000)) {
                log.error("签名验证失败:X-TIMESTAMP已过期，注意系统时间和服务器时间是否有误差！");
                throw new IllegalArgumentException("签名验证失败:X-TIMESTAMP已过期");
            }
        }

        //2.校验签名
        boolean isSigned = SignUtil.verifySign(allParams,headerSign);

        if (isSigned) {
            log.debug("Sign 签名通过！Header Sign : {}",headerSign);
            return true;
        } else {
            log.error("request URI = " + request.getRequestURI());
            log.error("Sign 签名校验失败！Header Sign : {}",headerSign);
            //校验失败返回前端
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = response.getWriter();
            Result<?> result = Result.error("Sign签名校验失败！");
            out.print(JSON.toJSON(result));
            return false;
        }
    }

}
