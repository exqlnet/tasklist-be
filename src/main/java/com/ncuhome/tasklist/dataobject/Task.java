package com.ncuhome.tasklist.dataobject;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ncuhome.tasklist.enums.TaskTypeEnum;
import com.ncuhome.tasklist.exception.TaskException;
import com.ncuhome.tasklist.form.TaskForm.CreateTaskForm;
import com.ncuhome.tasklist.form.TaskForm.ModifyTaskForm;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue
    private Integer taskId;

    private String title; // 任务标题

    @Lob
    private String description; // 任务描述

    private Integer isFinish = 0; // 1完成 0未完成

    private Date finishTime; // 完成时间

    private String label; // 标签：紧急、一般

    private Integer priority; // 1紧急, 0一般;










    // only use the
    private Date startTime;

    private Date endTime;

    private Date createTime = new Date(System.currentTimeMillis());

    private Date updateTime = new Date(System.currentTimeMillis());

    // week
    private Integer weekday;

    // month
    private Integer monthday;

    private Integer type;// 任务类型（周期）



    // relationship
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="userId", referencedColumnName = "userId")
    @JsonIgnore
    private User user;

    // type:
    // day : startTime endTime
    // once: startTime endTime  or  null
    // week:
    // month:
    // 1. 找出所有type=once的指定日期内的任务和所有type!=once的所有任务
    // 2. 遍历任务，把任务到每一天，返回一个字典数据

    private Boolean checkLabel(String label){
        String accessLabels[] = {"紧急", "一般"};
        for(String accessLabel :accessLabels){
            if(accessLabel.equals(label)){
                return true;
            }
        }
        return false;
    }

    public Task(String title, String description, Integer isFinish, Date finishTime, String label, Integer priority, Date startTime, Date endTime, Date createTime, Date updateTime, Integer weekday, Integer monthday, Integer type, User user) {
        this.title = title;
        this.description = description;
        this.isFinish = isFinish;
        this.finishTime = finishTime;
        this.label = label;
        this.priority = priority;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.weekday = weekday;
        this.monthday = monthday;
        this.type = type;
        this.user = user;
    }

    public Task(){}

    public Boolean modify(ModifyTaskForm modifyTaskForm){
        return true;
    }
}
