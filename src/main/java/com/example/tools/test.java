package com.example.tools;

import org.testng.annotations.Test;

import java.util.UUID;

/**
 * @Auther: Leo.hu
 * @Date: 2019/12/11 14:15
 * @Description:
 */
public class test {

    @Test
    public void f(){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(uuid);
    }

}
