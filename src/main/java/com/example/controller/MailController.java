package com.example.controller;

import com.example.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * @Auther: Leo.hu
 * @Date: 2019/12/18 13:58
 * @Description:
 */

@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @PostConstruct
    public void init() {
        MailController.getInstance().mailService = this.mailService;
    }

    private static class SingletonHolder {
        private static final MailController INSTANCE = new MailController();
    }

    private MailController() {
    }

    public static final MailController getInstance() {
        return MailController.SingletonHolder.INSTANCE;
    }

    /**
     * 实现单例 end
     */
    public MailService getMailService() {
        return MailController.getInstance().mailService;
    }

}
