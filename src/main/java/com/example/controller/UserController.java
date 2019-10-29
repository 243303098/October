package com.example.controller;

import com.example.model.UserInfo;
import com.example.service.UserService;
import com.example.util.AjaxObject;
import com.example.util.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/10/17 13:24
 * @Description:
 */

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public AjaxObject createUser(@RequestBody UserInfo user) throws Exception {
        if (userService.createUser(user)) {
            return AjaxObject.ok().put("msg", "success", "code", 1);
        } else {
            return AjaxObject.ok().put("msg", "fail", "code", 0);
        }
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public AjaxObject getUserInfo() {
        List<UserInfo> userInfoList = userService.getUserInfo();
        return AjaxObject.ok().put("userInfoList", userInfoList);
    }


}
