package com.example.util;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.example.actions.ByType;
import com.example.model.UIStep;
import org.openqa.selenium.By;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

/**
 * @Auther: Leo.hu
 * @Date: 2019/11/21 17:28
 * @Description:
 */
@Component
@RabbitListener(queues = "ExcuteTest")
public class ExcuteCase {

    @RabbitHandler
    public void excute(List<UIStep> uiStepList){
        Configuration.browser = "Chrome";
        Configuration.baseUrl = "https://www.baidu.com";
        open("/");
        //$(By.xpath("//input[@id = 'kw']")).setValue("123");
        System.out.println($(By.className("s_ipt")));
        $(By.className("s_ipt")).setValue("234");
        System.out.println(title());
        refresh();
        sleep(3000);
    }


    private SelenideElement getElement(List<UIStep> uiStepList) {
        String locationType = "111";
        String path = "";
        switch (locationType) {
            case ByType.ID:
                return $(By.id(path));
            case ByType.NAME:
                return $(By.name(path));
            case ByType.XPATH:
                return $(By.xpath(path));
            case ByType.CSSSELECTOR:
                return $(By.cssSelector(path));
            case ByType.CLASSNAME:
                return $(By.className(path));
            case ByType.LINKTEXT:
                return $(By.linkText(path));
            case ByType.PARTIALLINKTEXT:
                return $(By.partialLinkText(path));
            case ByType.TAGNAME:
                return $(By.tagName(path));
            default:
                return $(By.xpath(path));
        }
    }

}
