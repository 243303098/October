package com.example.controller;

import com.example.service.ExcuteStepDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * @Auther: Leo.hu
 * @Date: 2019/12/11 16:48
 * @Description:
 */

@RestController
public class ExcuteStepDetailsController {

    @Autowired
    private ExcuteStepDetailsService excuteStepDetailsService;

    @PostConstruct
    public void init() {
        ExcuteStepDetailsController.getInstance().excuteStepDetailsService = this.excuteStepDetailsService;
    }

    private static class SingletonHolder {
        private static final ExcuteStepDetailsController INSTANCE = new ExcuteStepDetailsController();
    }

    private ExcuteStepDetailsController() {
    }

    public static final ExcuteStepDetailsController getInstance() {
        return ExcuteStepDetailsController.SingletonHolder.INSTANCE;
    }

    /**
     * 实现单例 end
     */
    public ExcuteStepDetailsService getExcuteStepDetailsService() {
        return ExcuteStepDetailsController.getInstance().excuteStepDetailsService;
    }

}
