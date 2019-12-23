package com.example.service;

/**
 * @Auther: Leo.hu
 * @Date: 2019/12/18 13:34
 * @Description:
 */
public interface MailService {

    /**
     * 发送普通文本邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendSimpleMail(String to, String subject, String content);

}
