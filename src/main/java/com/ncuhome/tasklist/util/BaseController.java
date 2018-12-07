package com.ncuhome.tasklist.util;


import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import com.ncuhome.tasklist.dataobject.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class BaseController {

    @Resource
    private HttpServletRequest request;

    protected User getUser(){
        return (User) request.getAttribute("user");
    }

    protected JsonNode getBody(){
        return (JsonNode) request.getAttribute("body");
    }

}
