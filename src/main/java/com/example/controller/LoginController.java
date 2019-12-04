package com.example.controller;

import com.example.model.UserInfo;
import com.example.service.UserService;
import com.example.tools.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/10/15 16:34
 * @Description:
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public ModelAndView loginPage(ModelAndView modelAndView) {
        modelAndView.setViewName("/login");
        return modelAndView;
    }

    @RequestMapping(value = "/loginPost", method = RequestMethod.POST)
    public ModelAndView login(String userName, String password, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (StringUtil.isNull(userName) || StringUtil.isNull(password)) {
            modelAndView.addObject("msg", "登录失败，用户名或密码不能为空");
        } else {
            List<UserInfo> userInfoList = userService.selectUserInfoByUserNameAndPassword(userName, password);
            if (userInfoList.size() > 0) {
                request.getSession().setAttribute("user", userInfoList.get(0).getId());
                request.getSession().setAttribute("userName", userInfoList.get(0).getUsername());
                modelAndView.addObject("userName",userName);
                modelAndView.setViewName("/dashboard");
            } else {
                modelAndView.addObject("msg", "登录失败，用户名或密码错误");
            }
        }
        return modelAndView;
    }
}

