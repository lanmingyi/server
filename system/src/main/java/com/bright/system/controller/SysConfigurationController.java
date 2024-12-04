//package com.bright.system.controller;
//
//import cn.ewsd.common.bean.LoginInfo;
//import cn.ewsd.common.utils.BaseUtils;
//import cn.hutool.core.util.StrUtil;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.misboot.system.model.SysConfiguration;
//import com.misboot.system.service.SysConfigurationService;
//import com.misboot.system.utils.PageParam;
//import com.misboot.system.utils.PageSet;
//import com.misboot.system.vo.*;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiOperation;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Date;
//import java.util.List;
//
///**
// * 常用配置
// *
// * @Author 明成
// * @Email
// * @Date 2020-10-04 17:07:16
// */
//@Api(tags = {"常用配置"})
//@Controller
//@RequestMapping("/sysConfiguration")
//public class SysConfigurationController extends SystemBaseController {
//
//    @Autowired
//    private SysConfigurationService sysConfigurationService;
//
//
//    @ResponseBody
//    @RequestMapping(value = "/getPageSet", method = RequestMethod.POST)
//    public Object getPageSet(PageParam pageParam) {
//        String filterSort = "";
//        filterSort = BaseUtils.filterSort(request, filterSort + getAuthFilter());
//        PageSet<SysConfiguration> pageSet = sysConfigurationService.getPageSet(pageParam, filterSort);
//        return pageSet;
//    }
//
//
//    @ResponseBody
//    @RequestMapping(value = "/getDetailByUuid")
//    public Object getDetailByUuid(String uuid) {
//        if (StrUtil.isBlank(uuid)) {
//            return failure("必要参数不能为空！！！");
//        }
//        SysConfiguration sysConfiguration = sysConfigurationService.selectByPrimaryKey(uuid);
//        switch (sysConfiguration.getType()) {
//            case "login":
//                return JSONObject.parseObject(sysConfiguration.getJsonData(), LoginTab.class);
//            case "app":
//                return JSONObject.parseObject(sysConfiguration.getJsonData(), AppTab.class);
//            case "dingding":
//                return JSONObject.parseObject(sysConfiguration.getJsonData(), DingdingTab.class);
//            case "message":
//                return JSONObject.parseObject(sysConfiguration.getJsonData(), MessageTab.class);
//            case "mail":
//                return JSONObject.parseObject(sysConfiguration.getJsonData(), MailTab.class);
//            case "weixin":
//                return JSONObject.parseObject(sysConfiguration.getJsonData(), WeixinTab.class);
//            case "fileUpload":
//                return JSONObject.parseObject(sysConfiguration.getJsonData(), FileUplaodTab.class);
//            case "other":
//                return JSONObject.parseObject(sysConfiguration.getJsonData(), OtherTab.class);
//
//        }
//        return null;
//    }
//
//
//    @ResponseBody
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public Object save(@ModelAttribute SysConfiguration sysConfiguration) {
//        SysConfiguration sysConfiguration1 = sysConfigurationService.selectByPrimaryKey(sysConfiguration.getUuid());
//        if (null == sysConfiguration1) {
//            sysConfiguration.setJsonData(JSON.toJSONString(sysConfiguration));
//            sysConfiguration.setCreatorId(this.getCurrentUserNameId());
//            sysConfiguration.setCreator(this.getCurrentUserName());
//            sysConfiguration.setCreateTime(new Date());
//            sysConfiguration.setCreatorOrgId(Integer.parseInt(LoginInfo.getOrgId()));
//            int result = sysConfigurationService.insertSelective(getSaveData(sysConfiguration));
//        } else {
//            sysConfigurationService.updateByPrimaryKeySelective(getUpdateData(sysConfiguration));
//        }
//        return success("保存成功！");
//    }
//
//
//    @ResponseBody
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public Object update(@ModelAttribute SysConfiguration sysConfiguration) {
//        int result = sysConfigurationService.updateByPrimaryKeySelective(getUpdateData(sysConfiguration));
//        return result > 0 ? success("更新成功！") : failure("更新失败！");
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
//    public Object deleteBatch(@RequestParam String[] uuid) {
//        int result = sysConfigurationService.executeDeleteBatch(uuid);
//        return result > 0 ? success("删除成功！") : failure("删除失败！");
//    }
//
//
//    @ApiOperation(value = "获取常用配置")
//    @ApiImplicitParam(name = "type", value = "{weixin,mail,app,dingding,login,fileUpload,message,other}", required = true, dataType = "String")
//    @ResponseBody
//    @PostMapping(value = {"/getSysConfigurationDetailByType", "/getSysConfigurationDetailByType.api"})
//    public Object getSysConfigurationDetailByType(String type) {
//        if (StringUtils.isBlank(type)) {
//            return failure("类型不能为空!!!");
//        }
//        List<SysConfiguration> sysConfiguration = sysConfigurationService.getDetailType(type);
//        if (null == sysConfiguration || sysConfiguration.size() == 0) {
//            return failure("根据该" + type + "类型查询不到数据!!!");
//        }
//        if (sysConfiguration.size() > 1) {
//            return failure("根据该" + type + "类型查询到多条数据，请联系管理员!!!");
//        }
//        switch (sysConfiguration.get(0).getType()) {
//            case "login":
//                return JSONObject.parseObject(sysConfiguration.get(0).getJsonData(), LoginTab.class);
//            case "app":
//                return JSONObject.parseObject(sysConfiguration.get(0).getJsonData(), AppTab.class);
//            case "dingding":
//                return JSONObject.parseObject(sysConfiguration.get(0).getJsonData(), DingdingTab.class);
//            case "message":
//                return JSONObject.parseObject(sysConfiguration.get(0).getJsonData(), MessageTab.class);
//            case "mail":
//                return JSONObject.parseObject(sysConfiguration.get(0).getJsonData(), MailTab.class);
//            case "weixin":
//                return JSONObject.parseObject(sysConfiguration.get(0).getJsonData(), WeixinTab.class);
//            case "fileUpload":
//                return JSONObject.parseObject(sysConfiguration.get(0).getJsonData(), FileUplaodTab.class);
//            case "other":
//                return JSONObject.parseObject(sysConfiguration.get(0).getJsonData(), OtherTab.class);
//
//        }
//        return null;
//    }
//
//
//
//}
