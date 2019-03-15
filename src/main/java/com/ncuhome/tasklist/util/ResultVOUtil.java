package com.ncuhome.tasklist.util;

import com.ncuhome.tasklist.VO.ResultVO;
import com.ncuhome.tasklist.enums.HttpEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Component
public class ResultVOUtil {

    @Resource
    private HttpServletResponse response;

    public ResultVO success(String message){
        return new ResultVO<>(1, message,null);
    }
    public ResultVO success(String message, Object ob){
        return new ResultVO<>(1, message, ob);
    }
    public ResultVO success(Object ob){
        return new ResultVO<>(1, "获取成功", ob);
    }
    public ResultVO fromEnum(HttpEnum httpEnum){
        Integer httpCode = httpEnum.getHttpCode();
        response.setStatus(httpCode);
        if(httpCode >= 200 && httpCode < 300){
            return new ResultVO<>(1, httpEnum.getMessage(), null);
        }
        else{
            return new ResultVO<>(0, httpEnum.getMessage(), null);
        }
    }
    public ResultVO fromEnum(HttpEnum httpEnum, Object object){
        Integer httpCode = httpEnum.getHttpCode();
        response.setStatus(httpCode);
        if(httpCode >= 200 && httpCode < 300){
            return new ResultVO<>(1, httpEnum.getMessage(), object);
        }
        else{
            return new ResultVO<>(0, httpEnum.getMessage(), object);
        }

    }
    public static ResultVO success(){
        return new ResultVO<>(1, "操作成功", null);
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
