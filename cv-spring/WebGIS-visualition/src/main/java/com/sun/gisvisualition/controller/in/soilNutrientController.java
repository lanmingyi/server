package com.sun.gisvisualition.controller.in;

import com.sun.gisvisualition.dao.tb_soilNutrientsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("soilNutrient")
public class soilNutrientController {

    @Autowired
    private tb_soilNutrientsDao tbSoilNutrientsDao;


    @RequestMapping("getUrl")
    @ResponseBody
    public List<Map<String, Object>> getUrl( String date) {
        List<Map<String, Object>> url = tbSoilNutrientsDao.getUrl(date);
        return url;
    }
}
