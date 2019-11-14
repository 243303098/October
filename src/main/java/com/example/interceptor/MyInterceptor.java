package com.example.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * description:
 * author:cb
 * datetime:2019年7月28日  下午6:05:42
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler)throws Exception {

        HttpSession session = request.getSession(true);
        Object userId=session.getAttribute("user");
        if(null!=userId) {//已登录
            return true;
        }else {//未登录
            //直接重定向到登录页面
            response.sendRedirect("/login");
            return false;
        }
    }

}