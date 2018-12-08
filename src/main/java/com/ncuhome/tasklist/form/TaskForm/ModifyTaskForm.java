package com.ncuhome.tasklist.form.TaskForm;

import com.ncuhome.tasklist.enums.TaskTypeEnum;
import lombok.Data;

import javax.validation.Valid;
import java.util.Date;


@Data
public class ModifyTaskForm{

    @Valid
    private Integer taskId;

    private String title;

    private String description = "";

    private TaskTypeEnum type;

    private Date startTime;

    private Date endTime;

    private Integer weekday;

    private Integer monthday;

    private String label;

}