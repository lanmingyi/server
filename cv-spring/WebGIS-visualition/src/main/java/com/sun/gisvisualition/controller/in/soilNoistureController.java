package com.sun.gisvisualition.controller.in;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.gisvisualition.dao.tb_meteorologicalDao;
import com.sun.gisvisualition.entity.tb_meteorological;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("soilMoisture")
public class soilNoistureController {

    @Autowired
    private tb_meteorologicalDao tbMeteorologicalDao;

    //获取温度和湿度
    @RequestMapping("getIn")
    @ResponseBody
    public String get(String date, String type) {
        List<tb_meteorological> meteorologicals = tbMeteorologicalDao.get(date);
        JsonArray jsonArray = new JsonArray();
        for (tb_meteorological meteorological : meteorologicals) {
            JsonObject jsonObject = new JsonObject();
            JsonObject attributes = new JsonObject();
            JsonObject geometry = new JsonObject();
            if (type.equals("temperature")) {
                attributes.addProperty("Num", meteorological.getTemperature());
            }
            if (type.equals("humidity")) {
                attributes.addProperty("Num", meteorological.getHumidity());
            }
            geometry.addProperty("y", meteorological.getLatitude());
            geometry.addProperty("x", meteorological.getLongitude());
            jsonObject.add("attributes", attributes);
            jsonObject.add("geometry", geometry);
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }
}
