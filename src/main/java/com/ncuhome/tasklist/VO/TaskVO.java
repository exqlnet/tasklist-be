package com.ncuhome.tasklist.VO;

import com.ncuhome.tasklist.dataobject.Task;
import lombok.Data;
import org.hibernate.boot.model.relational.SimpleAuxiliaryDatabaseObject;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class TaskVO {
    private Integer taskId; // 任务ID

    private String title; // 任务标题

    private String description; // 任务描述

    private Integer isFinish = 0; // 1完成 0未完成

    private Date finishTime; // 完成时间

    private String label; // 标签：紧急、一般

    private Integer priority; // 1紧急, 0一般;

    // only use the
    private String startDate;

    private String startTime;

    private Date createTime = new Date(System.currentTimeMillis());

    private Date updateTime = new Date(System.currentTimeMillis());

    // week
//    private Integer weekday;

    // month
//    private Integer monthday;

//    private Integer type;// 任务类型（周期）


    public TaskVO(Task task) {
        BeanUtils.copyProperties(task, this);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        this.startDate = simpleDateFormat.format(task.getStartTime());
        this.startTime = new SimpleDateFormat("HH:mm").format(task.getStartTime());
    }

    public TaskVO(){}
}
