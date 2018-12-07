package com.ncuhome.tasklist.VO;

import javax.persistence.GeneratedValue;
import javax.persistence.Lob;
import java.io.Serializable;
import java.util.Date;

public class TaskVO implements Serializable {
    private Integer taskId;

    private String title;

    private String description;

    private Integer isFinish; // 1完成 0未完成

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
}
