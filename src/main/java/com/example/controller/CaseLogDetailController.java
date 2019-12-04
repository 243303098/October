package com.example.controller;

import com.example.model.CaseLog;
import com.example.model.CaseLogDetail;
import com.example.service.CaseLogDetailService;
import com.example.service.CaseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;

/**
 * @Auther: Leo.hu
 * @Date: 2019/11/27 21:40
 * @Description:
 */
@RestController
public class CaseLogDetailController {

    @Autowired
    private CaseLogDetailService caseLogDetailService;

    @PostConstruct
    public void init() {
        CaseLogDetailController.getInstance().caseLogDetailService = this.caseLogDetailService;
    }

    private static class SingletonHolder {
        private static final CaseLogDetailController INSTANCE = new CaseLogDetailController();
    }

    private CaseLogDetailController() {
    }

    public static final CaseLogDetailController getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 实现单例 end
     */
    public CaseLogDetailService getcaseLogDetailService() {
        return CaseLogDetailController.getInstance().caseLogDetailService;
    }

    @RequestMapping(value = "/uiTest/excuteLogDetails", method = RequestMethod.GET)
    public ModelAndView getLogDetails(Integer caseLogId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("caseLogDetailsList",caseLogDetailService.selectByCaseId(caseLogId));
        modelAndView.setViewName("uiTest/uiExcuteLogDetails");
        return modelAndView;
    }

}
