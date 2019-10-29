package com.example.service.impl;

import com.example.mapper.UserInfoMapper;
import com.example.model.UserInfo;
import com.example.service.UserService;
import com.example.util.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Leo.hu
 * @Date: 2019/10/17 11:49
 * @Description:
 */

@Service
public class UserServiceImpl extends BaseService<UserInfo> implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public boolean createUser(UserInfo user){
        if (super.save(user) > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<UserInfo> getUserInfo() {
        return userInfoMapper.selectAll();
    }

    @Override
    public List<UserInfo> selectUserInfoByUserNameAndPassword(String username, String password) {
        return userInfoMapper.selectUserInfoByUserNameAndPassword(username, password);
    }


}
