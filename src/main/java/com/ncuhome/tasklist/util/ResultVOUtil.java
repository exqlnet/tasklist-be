package com.ncuhome.tasklist.util;

import com.google.gson.Gson;
import com.ncuhome.tasklist.VO.ResultVO;
import com.ncuhome.tasklist.enums.LoginEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Component
public class ResultVOUtil {

    public ResultVO success(String message){
        return new ResultVO<>(1, message,null);
    }
    public ResultVO success(String message, Object ob){
        return new ResultVO<>(1, message, ob);
    }
    public ResultVO success(Object ob){
        return new ResultVO<>(1, "获取成功", ob);

    }
    public ResultVO error(String message, Object object){
        return new ResultVO<>(0, message, object);
    }
    public ResultVO error(String message){
        return new ResultVO<>(0, message, null);
    }

    public ResultVO error(){
        return new ResultVO<>(0, "获取失败", null);
    }
}
