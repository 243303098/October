package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: Leo.hu
 * @Date: 2019/10/22 16:23
 * @Description:
 */

@RestController
public class IndexController {

    @RequestMapping(value = "/baseConfig/dashboard")
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("/dashboard");
        return modelAndView;
    }

}
