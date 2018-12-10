package com.ncuhome.tasklist.form.TaskForm;

import lombok.Data;

import javax.validation.Valid;
import java.util.Date;

@Data
public class CreateTaskForm {

    @Valid
    private String title;

    private String description = "";

    private String type;

    @Valid
    private String startDate; // yyyy-MM-ddT16:00:00

    @Valid
    private String startTime; // 12:00

    private Date endTime;

    private Integer weekday;

    private Integer monthday;

    private String label; // 工作 学习 生活 购物 娱乐 或自定义

    private Integer priority;
}
