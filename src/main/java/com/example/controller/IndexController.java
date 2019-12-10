package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Auther: Leo.hu
 * @Date: 2019/10/22 16:23
 * @Description:
 */

@RestController
public class IndexController {

    @RequestMapping(value = "/baseConfig/dashboard")
    public ModelAndView index(ModelAndView modelAndView, HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer userId = Integer.valueOf(session.getAttribute("user").toString());
        modelAndView.addObject("userId", userId);
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }

}
