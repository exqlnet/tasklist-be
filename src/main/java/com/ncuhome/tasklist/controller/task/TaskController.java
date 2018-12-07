package com.ncuhome.tasklist.controller.task;


import com.fasterxml.jackson.databind.ser.Serializers;
import com.google.gson.Gson;
import com.ncuhome.tasklist.VO.ResultVO;
import com.ncuhome.tasklist.annotations.LoginRequired;
import com.ncuhome.tasklist.dataobject.Task;
import com.ncuhome.tasklist.form.TaskForm.CreateTaskForm;
import com.ncuhome.tasklist.form.TaskForm.ModifyTaskForm;
import com.ncuhome.tasklist.service.TaskService;
import com.ncuhome.tasklist.util.BaseController;
import com.ncuhome.tasklist.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.event.ObjectChangeListener;
import javax.validation.Valid;
import javax.xml.transform.Result;
import java.util.Collections;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/task")
public class TaskController extends BaseController {

    @Autowired
    private TaskService taskService;


    @LoginRequired
    @PostMapping("/createTask")
    public Object createTask(@RequestBody @Valid CreateTaskForm createTaskForm){
        taskService.createTask(createTaskForm);
        return ResultVOUtil.success("ok");
    }

    @LoginRequired
    @DeleteMapping("/deleteTask")
    public Object deleteTask(@RequestBody @Valid Integer taskId){
        taskService.deleteTask(taskId);
        return ResultVOUtil.success("ok");
    }

    @LoginRequired
    @PutMapping("/modifyTask")
    public Object modifyTask(@RequestBody @Valid ModifyTaskForm modifyTaskForm){
        taskService.modifyTask(modifyTaskForm);
        return ResultVOUtil.success("ok");
    }

    @LoginRequired
    @GetMapping("/list")
    public Object listTask(){
        log.info("{}", getUser().getTasks());
        List<Task> list = getUser().getTasks();
        for(Task t :  list){
            log.info("{}", t.getTaskId());
        }
        return ResultVOUtil.success(getUser().getTasks());
    }
}
