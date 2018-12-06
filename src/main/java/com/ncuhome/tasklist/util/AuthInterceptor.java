package com.ncuhome.tasklist.util;

import com.ncuhome.tasklist.annotations.LoginRequired;
import com.ncuhome.tasklist.dataobject.User;
import com.ncuhome.tasklist.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 判断接口是否需要登录
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        if(methodAnnotation==null)return true;
        User user = userService.verifyToken(request.getHeader("Authorization"));
        request.setAttribute("user", user);
        return true;
    }
}
