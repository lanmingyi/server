package com.brightcomplete.config.filter;

import com.brightcomplete.config.sign.util.BodyReaderHttpServletRequestWrapper;
import com.brightcomplete.common.constant.CommonConstant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 针对post请求，将HttpServletRequest包一层 保留body里的参数
 * @Author lmy
 * @Date 2022/4/25 19:19
 **/
public class RequestBodyReserveFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;

        if(servletRequest instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            // POST请求类型，才获取POST请求体
            if(CommonConstant.HTTP_POST.equals(req.getMethod())){
                requestWrapper = new BodyReaderHttpServletRequestWrapper(req);
            }
        }

        if(requestWrapper == null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(requestWrapper, servletResponse);
        }
    }
}
