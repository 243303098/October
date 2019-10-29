package com.example.mapper;

import com.example.model.UserInfo;
import com.example.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserInfoMapper extends MyMapper<UserInfo> {

    List<UserInfo> selectUserInfoByUserNameAndPassword(@Param("username") String username, @Param("password")String password);

}