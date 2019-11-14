package com.example.controller;

import com.example.service.UIKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: Leo.hu
 * @Date: 2019/10/30 10:36
 * @Description:
 */
@RestController
public class UIKeywordController {

    @Autowired
    private UIKeywordService uiKeywordService;

    @RequestMapping(value = "/uiTest/keywordManage", method = RequestMethod.GET)
    public ModelAndView toUIKeywordPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("uiTest/keywordManage");
        modelAndView.addObject("keywordList", uiKeywordService.getAllUIKeyword());
        return modelAndView;
    }

    @RequestMapping(value = "/uiTest/keywordManage", method = RequestMethod.POST)
    public ModelAndView getUIKeywordByID(String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("uiTest/keywordManage");
        modelAndView.addObject("keywordList", uiKeywordService.getUIKeywordById(id));
        return modelAndView;
    }
}
