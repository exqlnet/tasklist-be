package com.ncuhome.tasklist.enums;

import lombok.Getter;
import org.springframework.stereotype.Component;


@Getter
public enum TaskTypeEnum {
    ONCE(1, "无循环"),
    DAY(2, "每天"),
    WEEK(3, "每周"),
    MONTH(4, "每月");

    private Integer code;

    private String desc;

    TaskTypeEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static Integer StringToInteger(String typeString){
        for(TaskTypeEnum taskTypeEnum: values()){
            if(taskTypeEnum.getDesc().equals(typeString)){
                return taskTypeEnum.getCode();
            }
        }
        return -1;
    }
}
