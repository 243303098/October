package com.example.service;


import com.example.model.UserInfo;
import com.example.util.Page;

import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/10/17 11:48
 * @Description:
 */
public interface UserService extends IService<UserInfo>{

    boolean createUser(UserInfo userInfo) throws Exception;

    List<UserInfo> getUserInfo();

    List<UserInfo> selectUserInfoByUserNameAndPassword(String username, String password);

}
