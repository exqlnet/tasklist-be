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

    @Resource
    private HttpServletResponse response;

    public ResultVO success(Object object){
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(1);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public ResultVO error(Object object){
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("失败");
        resultVO.setData(object);
        return resultVO;
    }

    public ResultVO error(Object object, Integer status){
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("失败");
        resultVO.setData(object);
        response.setStatus(status);
        return resultVO;
    }
}
