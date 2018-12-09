package com.ncuhome.tasklist.enums;

import lombok.Getter;
import org.springframework.stereotype.Component;


@Getter
public enum TaskTypeEnum {
    ONCE(1, "一次性任务"),
    DAY(2, "每天任务"),
    WEEK(3, "每周任务"),
    MONTH(4, "每月任务");

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
