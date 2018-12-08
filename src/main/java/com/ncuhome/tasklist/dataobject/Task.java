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

    private String title;

    @Lob
    private String description;

    private Integer isFinish = 0; // 1完成 0未完成

    private Date finishTime; // 完成时间

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

    private String label; // 标签：紧急、一般

    private Integer priority; // 优先级;

    // relationship
    @ManyToOne
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

    public Task(CreateTaskForm createTaskForm){
        title = createTaskForm.getTitle();
        description = createTaskForm.getDescription();
        label = createTaskForm.getLabel();
        priority = createTaskForm.getPriority();
        startTime = createTaskForm.getStartTime();
        isFinish = 0;



////        if(!checkLabel(label)){
////            throw new TaskException("Invalid label");
////        }
//
//
//        if (createTaskForm.getType().equals(TaskTypeEnum.ONCE.getCode())){
//            type = TaskTypeEnum.ONCE.getCode();
//            startTime = createTaskForm.getStartTime();
//            endTime = createTaskForm.getEndTime();
//        }
//        else if (createTaskForm.getType().equals(TaskTypeEnum.DAY.getCode())){
//            // 不允许跨越两天
////            if(createTaskForm.getStartTime().)
//            type = TaskTypeEnum.DAY.getCode();
//            startTime = createTaskForm.getStartTime();
//            endTime = createTaskForm.getEndTime();
//        }
//        else if (createTaskForm.getType().equals(TaskTypeEnum.WEEK.getCode())){
//            type = TaskTypeEnum.WEEK.getCode();
//            startTime = createTaskForm.getStartTime();
//            endTime = createTaskForm.getEndTime();
//            weekday = createTaskForm.getWeekday();
//            if (weekday <= 0 || weekday > 7){
//                throw new TaskException("weekday有误");
//            }
//        }
//        else if (createTaskForm.getType().equals(TaskTypeEnum.MONTH.getCode())){
//            type = TaskTypeEnum.WEEK.getCode();
//            startTime = createTaskForm.getStartTime();
//            endTime = createTaskForm.getEndTime();
//            monthday = createTaskForm.getMonthday();
//            if(monthday<=0 || monthday >=32){
//                throw new TaskException("monthday有误");
//            }
//        }
//        else throw new TaskException("error type");
    }
    public Task(){}

    public Boolean modify(ModifyTaskForm modifyTaskForm){
        return true;
    }
}
