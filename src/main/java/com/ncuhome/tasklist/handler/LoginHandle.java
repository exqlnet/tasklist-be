package com.ncuhome.tasklist.handler;

import com.ncuhome.tasklist.VO.ResultVO;
import com.ncuhome.tasklist.util.ResultVOUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class LoginHandle {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVO handle(Exception e){
        return ResultVOUtil.error(e.getMessage());
    }
}
