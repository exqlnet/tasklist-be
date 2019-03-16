package com.ncuhome.tasklist.util;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ncuhome.tasklist.annotations.LoginRequired;
import com.ncuhome.tasklist.dataobject.User;
import com.ncuhome.tasklist.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.WeakHashMap;
import java.util.stream.Collectors;


@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    UserService userService;

    @Autowired
    User currentUser;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
//        log.info("{}", handlerMethod.getMethodAnnotation(LoginRequired.class));

        // 判断接口是否需要登录
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
//        log.info("{}", method);
//        log.info("{}", methodAnnotation);
        if(methodAnnotation==null)return true;

        if(currentUser.getUserId() == null){
            unauth(response);
            return false;
        }
        return true;
    }


    private static void unauth(HttpServletResponse response) throws IOException{
        response.setStatus(401);
        response.setHeader("Content-Type", "application/json");
        response.setCharacterEncoding("UTF-8");
        Map<String, Object> body = new HashMap<>();
        body.put("status", 401);
        body.put("message", "Unauthorized token");
        response.getWriter().write(JsonUtil.gson.toJson(body));
    }
}
