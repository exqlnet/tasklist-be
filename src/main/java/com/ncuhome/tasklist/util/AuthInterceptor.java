package com.ncuhome.tasklist.util;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

//        // 获取参数并转化成JsonNode，以便直接在baseController下获取body
//        String postData = extractPostRequestBody(request);
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode requestBody = mapper.readTree(postData);
//        request.setAttribute("body", requestBody);


        // 判断接口是否需要登录
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        if(methodAnnotation==null)return true;
        User user = userService.verifyToken(request.getHeader("Authorization"));

        if (user == null){unauth(response);return false;}

        request.setAttribute("user", user);
        return true;
    }

    static String extractPostRequestBody(HttpServletRequest request) throws IOException {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            Scanner s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }
        return "";
    }

    private static void unauth(HttpServletResponse response) throws IOException{
        response.setStatus(401);
        response.setHeader("Content-Type", "application/json");
        response.setCharacterEncoding("UTF-8");
        Map<String, Object> body = new HashMap<>();
        body.put("code", 401);
        body.put("msg", "Unauthorized token");
        response.getWriter().write(JsonUtil.gson.toJson(body));
    }
}
