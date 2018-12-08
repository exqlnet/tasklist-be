package com.ncuhome.tasklist.form.TaskForm;

import com.ncuhome.tasklist.enums.TaskTypeEnum;
import lombok.Data;

import javax.validation.Valid;
import java.util.Date;

@Data
public class CreateTaskForm {

    @Valid
    private String title;

    private String description = "";

    @Valid
    private Integer type;

    private Date startTime;

    private Date endTime;

    private Integer weekday;

    private Integer monthday;

    private String label; // 工作 学习 生活 购物 娱乐 或自定义

}
