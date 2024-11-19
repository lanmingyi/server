package com.sun.gisvisualition.controller.webSocket;

import com.sun.gisvisualition.config.webSocket.WebSocketServer;
import com.sun.gisvisualition.entity.DataResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/checkCenter")
public class CheckCenterController {

    //页面请求
    @GetMapping("/socket/{cid}")
    public ModelAndView socket(@PathVariable String cid) {
        ModelAndView mav = new ModelAndView("webSocket/webSocketTest");
        mav.addObject("cid", cid);
        return mav;
    }

    @RequestMapping("/socket/push/{cid}")
    @ResponseBody
    public DataResult pushToWeb(@PathVariable String cid, String message) {
        DataResult dataResult = new DataResult();
        try {
            WebSocketServer.sendInfo(message, cid);
        } catch (Exception e) {
            e.printStackTrace();
            dataResult.setMsg(cid + "#" + e.getMessage());
            return dataResult;
        }
        dataResult.setMsg(cid);
        return dataResult;
    }
}
