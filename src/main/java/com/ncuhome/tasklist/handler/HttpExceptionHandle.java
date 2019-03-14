package com.ncuhome.tasklist.handler;

import com.ncuhome.tasklist.VO.ResultVO;
import com.ncuhome.tasklist.exception.HttpException;
import com.ncuhome.tasklist.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class HttpExceptionHandle {

    @Autowired
    ResultVOUtil resultVOUtil;

    @Resource
    HttpServletResponse response;

    @ExceptionHandler(value = {HttpException.class})
    @ResponseBody
    public String handle(HttpException e){
        response.setStatus(e.getHttpCode());
        return e.getMessage();
    }
}
