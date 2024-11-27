package com.bright.system.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.bright.common.util.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bright.common.api.vo.Result;
import com.bright.common.constant.CommonConstant;
import com.bright.common.constant.SymbolConstant;
import com.bright.common.system.vo.LoginUser;
import com.bright.common.system.util.JwtUtil;
import com.bright.modules.base.service.BaseCommonService;
import com.bright.system.entity.SysDepart;
import com.bright.system.entity.SysUser;
import com.bright.system.model.SysLoginModel;
import com.bright.system.service.SysDepartService;
import com.bright.system.service.SysDictService;
import com.bright.system.service.SysUserService;
import com.bright.system.util.RandImageUtil;


@RestController
@RequestMapping("/sys")
@Api(tags = "用户登录")
@Slf4j
public class LoginController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SysDepartService sysDepartService;
    @Autowired
    private SysDictService sysDictService;
    @Resource
    private BaseCommonService baseCommonService;

    private final String BASE_CHECK_CODES = "qwertyuiplkjhgfdsazxcvbnmQWERTYUPLKJHGFDSAZXCVBNM1234567890";

    @ApiOperation("登录接口")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<JSONObject> login(@RequestBody SysLoginModel sysLoginModel) {
        Result<JSONObject> result = new Result<JSONObject>();
        String username = sysLoginModel.getUsername();
        String password = sysLoginModel.getPassword();
        // 暂时注释掉密码加密逻辑，有点问题
        // 前端密码加密，后端进行密码解密
        // password = AesEncryptUtil.desEncrypt(sysLoginModel.getPassword().replaceAll("%2B", "\\+")).trim();//密码解密

        // 校验验证码
        String captcha = sysLoginModel.getCaptcha();
        if (captcha == null) {
            result.error500("验证码无效");
            return result;
        }
        String lowerCaseCaptcha = captcha.toLowerCase();
        String realKey = Md5Util.md5Encode(lowerCaseCaptcha + sysLoginModel.getCheckKey(), "utf-8");
        Object checkCode = redisUtil.get(realKey);
        // 当进入登录页时，有一定几率出现验证码错误 #1714
        if (checkCode == null || !checkCode.toString().equals(lowerCaseCaptcha)) {
            log.warn("验证码错误，key= {} , Ui checkCode= {}, Redis checkCode = {}", sysLoginModel.getCheckKey(), lowerCaseCaptcha, checkCode);
            result.error500("验证码错误");
            // 改成特殊的code 便于前端判断
            result.setCode(HttpStatus.PRECONDITION_FAILED.value());
            return result;
        }

        // 1. 校验用户是否有效
        // 登录代码验证用户是否注销bug，if条件永远为false
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername, username);
        SysUser sysUser = sysUserService.getOne(queryWrapper);
        result = sysUserService.checkUserIsEffective(sysUser);
        if (!result.isSuccess()) {
            return result;
        }

        //2. 校验用户名或密码是否正确
        String userpassword = PasswordUtil.encrypt(username, password, sysUser.getSalt());
        String syspassword = sysUser.getPassword();
        if (!syspassword.equals(userpassword)) {
            result.error500("用户名或密码错误");
            return result;
        }

        //用户登录信息
        userInfo(sysUser, result);
        // 登录成功，删除redis中的验证码
        redisUtil.del(realKey);
        // 登录成功，删除redis中的验证码
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(sysUser, loginUser);
        baseCommonService.addLog("用户名: " + username + ",登录成功！", CommonConstant.LOG_TYPE_1, null, loginUser);
        return result;
    }

    /**
     * 用户信息
     *
     * @param sysUser
     * @param result
     * @return
     */
    private Result<JSONObject> userInfo(SysUser sysUser, Result<JSONObject> result) {
        String username = sysUser.getUsername();
        String syspassword = sysUser.getPassword();
        // 获取用户部门信息
        JSONObject obj = new JSONObject(new LinkedHashMap<>());

        // 生成token
        String token = JwtUtil.sign(username, syspassword);
        // 设置token缓存有效时间
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME * 2 / 1000);
        obj.put("token", token);

//        // 获取用户租户信息
//        String tenantIds = sysUser.getRelTenantIds();
//        if (ConvertUtils.isNotEmpty(tenantIds)) {
//            List<Integer> tenantIdList = new ArrayList<>();
//            for(String id: tenantIds.split(SymbolConstant.COMMA)){
//                tenantIdList.add(Integer.valueOf(id));
//            }
//            // 该方法仅查询有效的租户，如果返回0个就说明所有的租户均无效。
////            List<SysTenant> tenantList = sysTenantService.queryEffectiveTenant(tenantIdList);
////            if (tenantList.size() == 0) {
////                result.error500("与该用户关联的租户均已被冻结，无法登录！");
////                return result;
////            } else {
////                obj.put("tenantList", tenantList);
////            }
//        }

        obj.put("userInfo", sysUser);

        List<SysDepart> departs = sysDepartService.queryUserDeparts(sysUser.getId());
        obj.put("departs", departs);
        if (departs == null || departs.size() == 0) {
            obj.put("multi_depart", 0);
        } else if (departs.size() == 1) {
            sysUserService.updateUserDepart(username, departs.get(0).getOrgCode());
            obj.put("multi_depart", 1);
        } else {
            // 查询当前是否有登录部门
            // 如果用戶为选择部门，数据库为存在上一次登录部门，则取一条存进去
            SysUser sysUserById = sysUserService.getById(sysUser.getId());
            if (ConvertUtils.isEmpty(sysUserById.getOrgCode())) {
                sysUserService.updateUserDepart(username, departs.get(0).getOrgCode());
            }
            obj.put("multi_depart", 2);
        }
        obj.put("sysAllDictItems", sysDictService.queryAllDictItems());
        result.setData(obj);
        result.success("登录成功");
        return result;
    }

    @ApiOperation("获取验证码")
    @GetMapping("/randomImage/{key}")
    public Result<String> randomImage(HttpServletResponse response, @PathVariable("key") String key) {
        Result<String> res = new Result<String>();
        try {
            // 生成验证码
            String code = RandomUtil.randomString(BASE_CHECK_CODES, 4);

            // 存储到redis中
            String lowerCaseCode = code.toLowerCase();
            String realKey = Md5Util.md5Encode(lowerCaseCode + key, "utf-8");
            log.info("获取验证码，Redis checkCode = {}，key = {}", code, key);
            redisUtil.set(realKey, lowerCaseCode, 60);

            //返回前端
            String base64 = RandImageUtil.generate(code);
            res.setSuccess(true);
            res.setData(base64);
        } catch (Exception e) {
            res.error500("获取验证码出错" + e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    /**
     * app登录
     *
     * @param sysLoginModel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mLogin", method = RequestMethod.POST)
    public Result<JSONObject> mLogin(@RequestBody SysLoginModel sysLoginModel) throws Exception {
        Result<JSONObject> result = new Result<JSONObject>();
        String username = sysLoginModel.getUsername();
        String password = sysLoginModel.getPassword();

        //1. 校验用户是否有效
        SysUser sysUser = sysUserService.getUserByName(username);
        result = sysUserService.checkUserIsEffective(sysUser);
        if (!result.isSuccess()) {
            return result;
        }

        //2. 校验用户名或密码是否正确
        String userpassword = PasswordUtil.encrypt(username, password, sysUser.getSalt());
        String syspassword = sysUser.getPassword();
        if (!syspassword.equals(userpassword)) {
            result.error500("用户名或密码错误");
            return result;
        }

        String orgCode = sysUser.getOrgCode();
        if (ConvertUtils.isEmpty(orgCode)) {
            //如果当前用户无选择部门 查看部门关联信息
            List<SysDepart> departs = sysDepartService.queryUserDeparts(sysUser.getId());
            // 新建用户，没有设置部门及角色，点击登录提示暂未归属部，一直在登录页面 使用手机号登录 可正常
            if (departs == null || departs.size() == 0) {
				/*result.error500("用户暂未归属部门,不可登录!");
				return result;*/
            } else {
                orgCode = departs.get(0).getOrgCode();
                sysUser.setOrgCode(orgCode);
                this.sysUserService.updateUserDepart(username, orgCode);
            }
        }
        JSONObject obj = new JSONObject();
        //用户登录信息
        obj.put("userInfo", sysUser);

        // 生成token
        String token = JwtUtil.sign(username, syspassword);
        // 设置超时时间
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME * 2 / 1000);

        //token 信息
        obj.put("token", token);
        result.setData(obj);
        result.setCode(200);
//        result.setSuccess(true);
        baseCommonService.addLog("用户名: " + username + ",登录成功[移动端]！", CommonConstant.LOG_TYPE_1, null);
        return result;
    }


    /**
     * 短信验证码接口
     *
     * @param jsonObject
     * @return
     */
    @PostMapping(value = "/sms")
    public Result<String> sms(@RequestBody JSONObject jsonObject) {
        Result<String> result = new Result<String>();
        String mobile = jsonObject.get("mobile").toString();
        //手机号模式 登录模式: "2"  注册模式: "1"
        String smsmode=jsonObject.get("smsmode").toString();
        log.info(mobile);
        if(ConvertUtils.isEmpty(mobile)){
            result.setMessage("手机号不允许为空！");
            result.setSuccess(false);
            return result;
        }
        Object object = redisUtil.get(mobile);
        if (object != null) {
            result.setMessage("验证码10分钟内，仍然有效！");
            result.setSuccess(false);
            return result;
        }

        // 随机数
        String captcha = RandomUtil.randomNumbers(6);
        JSONObject obj = new JSONObject();
        obj.put("code", captcha);
        try {
            boolean b = false;
            // 注册模板
            if (CommonConstant.SMS_TPL_TYPE_1.equals(smsmode)) {
                SysUser sysUser = sysUserService.getUserByPhone(mobile);
                if(sysUser!=null) {
                    result.error500(" 手机号已经注册，请直接登录！");
                    baseCommonService.addLog("手机号已经注册，请直接登录！", CommonConstant.LOG_TYPE_1, null);
                    return result;
                }
                b = DySmsHelper.sendSms(mobile, obj, DySmsEnum.REGISTER_TEMPLATE_CODE);
            }else {
                // 登录模式，校验用户有效性
                SysUser sysUser = sysUserService.getUserByPhone(mobile);
                result = sysUserService.checkUserIsEffective(sysUser);
                if(!result.isSuccess()) {
                    String message = result.getMessage();
                    String userNotExist="该用户不存在，请注册";
                    if(userNotExist.equals(message)){
                        result.error500("该用户不存在或未绑定手机号");
                    }
                    return result;
                }

                /**
                 * smsmode 短信模板方式  0 .登录模板、1.注册模板、2.忘记密码模板
                 */
                if (CommonConstant.SMS_TPL_TYPE_0.equals(smsmode)) {
                    //登录模板
                    b = DySmsHelper.sendSms(mobile, obj, DySmsEnum.LOGIN_TEMPLATE_CODE);
                } else if(CommonConstant.SMS_TPL_TYPE_2.equals(smsmode)) {
                    //忘记密码模板
                    b = DySmsHelper.sendSms(mobile, obj, DySmsEnum.FORGET_PASSWORD_TEMPLATE_CODE);
                }
            }

            if (b == false) {
                result.setMessage("短信验证码发送失败,请稍后重试");
                result.setSuccess(false);
                return result;
            }
            //验证码10分钟内有效
            redisUtil.set(mobile, captcha, 600);
            // result.setResult(captcha);
            result.setSuccess(true);

        } catch (ClientException e) {
            e.printStackTrace();
            result.error500(" 短信接口未配置，请联系管理员！");
            return result;
        }
        return result;
    }


    /**
     * 手机号登录接口
     *
     * @param jsonObject
     * @return
     */
    @ApiOperation("手机号登录接口")
    @PostMapping("/phoneLogin")
    public Result<JSONObject> phoneLogin(@RequestBody JSONObject jsonObject) {
        Result<JSONObject> result = new Result<JSONObject>();
        String phone = jsonObject.getString("mobile");

        // 校验用户有效性
        SysUser sysUser = sysUserService.getUserByPhone(phone);
        result = sysUserService.checkUserIsEffective(sysUser);
        if(!result.isSuccess()) {
            return result;
        }

        String smscode = jsonObject.getString("captcha");
        Object code = redisUtil.get(phone);
        if (!smscode.equals(code)) {
//            result.setMessage("手机验证码错误");
            result.error500("手机验证码错误");
            return result;
        }
        // 用户信息
        userInfo(sysUser, result);
        // 添加日志
        baseCommonService.addLog("用户名: " + sysUser.getUsername() + ",登录成功！", CommonConstant.LOG_TYPE_1, null);

        return result;
    }
}
