package com.ncuhome.tasklist.handler;

import com.ncuhome.tasklist.VO.ResultVO;
import com.ncuhome.tasklist.exception.TaskException;
import com.ncuhome.tasklist.exception.UserLoginException;
import com.ncuhome.tasklist.exception.UserRegisterException;
import com.ncuhome.tasklist.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class LoginHandle {

    @Autowired
    ResultVOUtil resultVOUtil;

    @ExceptionHandler(value = {UserLoginException.class, TaskException.class, UserRegisterException.class})
    @ResponseBody
    public ResultVO handle(Exception e){
        return resultVOUtil.error(e.getMessage());
    }
}
