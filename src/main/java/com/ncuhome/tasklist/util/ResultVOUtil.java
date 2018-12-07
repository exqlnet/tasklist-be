package com.ncuhome.tasklist.util;

import com.google.gson.Gson;
import com.ncuhome.tasklist.VO.ResultVO;
import com.ncuhome.tasklist.enums.LoginEnum;
import org.springframework.stereotype.Component;

@Component
public class ResultVOUtil {

    static public ResultVO success(Object object){
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(1);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    static public ResultVO error(Object object){
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("失败");
        resultVO.setData(object);
        return resultVO;
    }
}
