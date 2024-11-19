package com.sun.gisvisualition.config.exception;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ControllerException {

    @Autowired
    private Gson gson;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    @ModelAttribute
    public void addAttributes(Model model) {
    }

    @ResponseBody
    @ExceptionHandler(value = DataResultException.class)
    public String errorHandler(DataResultException e) {
        return gson.toJson(e);
    }

    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public String serviceInsideException() {
        return gson.toJson(new DataResultException("请求缺少必要参数"));
    }
}
