package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.model.UserInfo;
import com.example.service.UserService;
import com.example.tools.StringUtil;
import com.example.util.AjaxObject;
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
    public AjaxObject login(@RequestBody String postData, HttpServletRequest request) {
        JSONObject userData = JSONObject.parseObject(postData);
        if (userData.containsKey("username") && userData.containsKey("password")) {
            if (StringUtil.isNull(userData.getString("username")) || StringUtil.isNull(userData.getString("password"))) {
                return AjaxObject.error(3, "用户名或密码为空");
            } else {
                List<UserInfo> userInfoList = userService.selectUserInfoByUserNameAndPassword(userData.getString("username"), userData.getString("password"));
                if (userInfoList.size() > 0) {
                    request.getSession().setAttribute("user", userInfoList.get(0).getUsername());
                    return AjaxObject.ok().put("msg", "success", "code", 1);
                } else {
                    return AjaxObject.ok().put("msg", "fail", "code", 2);
                }
            }
        }else {
            return AjaxObject.ok().put("msg", "fail", "code", 500);
        }
    }
}
