package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: Leo.hu
 * @Date: 2019/11/26 16:29
 * @Description:
 */
@RestController
public class ReportController {

    @RequestMapping(value = "/uiTest/report", method = RequestMethod.POST)
    public ModelAndView toReport(String param){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("report");
        return modelAndView;
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public ModelAndView toReportPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("report");
        return modelAndView;
    }

}
