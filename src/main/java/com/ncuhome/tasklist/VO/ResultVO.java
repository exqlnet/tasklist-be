package com.ncuhome.tasklist.VO;

import com.ncuhome.tasklist.enums.HttpEnum;
import lombok.Data;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Data
public class ResultVO<T> {

    // 状态码
    private Integer status;

    // 提示信息
    private String message;

    // 具体内容
    private T data;

    public ResultVO(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
