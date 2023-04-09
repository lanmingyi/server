package com.bright.system.controller;

import cn.hutool.core.util.RandomUtil;
import com.bright.common.api.vo.Result;
import com.bright.common.util.Md5Util;
import com.bright.common.util.RedisUtil;
import com.bright.system.util.RandImageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/sys")
@Api(tags="用户登录")
@Slf4j
public class LoginController {

    @Autowired
    private RedisUtil redisUtil;

    private final String BASE_CHECK_CODES = "qwertyuiplkjhgfdsazxcvbnmQWERTYUPLKJHGFDSAZXCVBNM1234567890";

    @ApiOperation("获取验证码")
    @GetMapping("/randomImage/{key}")
    public Result<String> randomImage(HttpServletResponse response, @PathVariable("key") String key){
        Result<String> res = new Result<String>();
        try{
            // 生成验证码
            String code = RandomUtil.randomString(BASE_CHECK_CODES,4);

            // 存储到redis中
            String lowerCaseCode = code.toLowerCase();
            String realKey = Md5Util.md5Encode(lowerCaseCode+key, "utf-8");
            log.info("获取验证码，Redis checkCode = {}，key = {}", code, key);
            redisUtil.set(realKey, lowerCaseCode, 60);

            //返回前端
            String base64 = RandImageUtil.generate(code);
            res.setSuccess(true);
            res.setResult(base64);
        } catch (Exception e) {
            res.error500("获取验证码出错"+e.getMessage());
            e.printStackTrace();
        }
        return res;
    }
}
