package com.example.controller;

import com.example.model.ExcuteLogDetails;
import com.example.service.ExcuteLogDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * @Auther: Leo.hu
 * @Date: 2019/12/11 16:46
 * @Description:
 */

@RestController
public class ExcuteLogDetailsController {

    @Autowired
    private ExcuteLogDetailsService excuteLogDetailsService;

    @PostConstruct
    public void init() {
        ExcuteLogDetailsController.getInstance().excuteLogDetailsService = this.excuteLogDetailsService;
    }

    private static class SingletonHolder {
        private static final ExcuteLogDetailsController INSTANCE = new ExcuteLogDetailsController();
    }

    private ExcuteLogDetailsController() {
    }

    public static final ExcuteLogDetailsController getInstance() {
        return ExcuteLogDetailsController.SingletonHolder.INSTANCE;
    }

    /**
     * 实现单例 end
     */
    public ExcuteLogDetailsService getExcuteLogDetailsService() {
        return ExcuteLogDetailsController.getInstance().excuteLogDetailsService;
    }

}
