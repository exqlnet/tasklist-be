package com.ncuhome.tasklist.dataobject;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
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

    private Integer isFinish; // 1完成 0未完成

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private Date updateTime;
}
