package com.ncuhome.tasklist.controller.task;


import com.google.gson.JsonObject;
import com.ncuhome.tasklist.annotations.LoginRequired;
import com.ncuhome.tasklist.dataobject.Task;
import com.ncuhome.tasklist.form.TaskForm.CreateTaskForm;
import com.ncuhome.tasklist.form.TaskForm.DeleteTaskForm;
import com.ncuhome.tasklist.form.TaskForm.ModifyTaskForm;
import com.ncuhome.tasklist.service.TaskService;
import com.ncuhome.tasklist.util.BaseController;
import com.ncuhome.tasklist.util.JsonUtil;
import com.ncuhome.tasklist.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/task")
public class TaskController extends BaseController {

    @Autowired
    private TaskService taskService;

    @Autowired
    ResultVOUtil resultVOUtil;

    @Autowired
    JsonUtil jsonUtil;

    @LoginRequired
    @PostMapping("/create")
    public Object createTask(@RequestBody @Valid CreateTaskForm createTaskForm) throws ParseException {
        taskService.createTask(createTaskForm);
        return resultVOUtil.success("创建成功");
    }

    @LoginRequired
    @DeleteMapping("/delete")
    public Object deleteTask(@RequestBody String jsonString){
        JsonObject body = JsonUtil.gson.fromJson(jsonString, JsonObject.class);
        Integer taskId = body.get("taskId").getAsInt();
        taskService.deleteTask(taskId);
        return resultVOUtil.success("删除成功");
    }

    @LoginRequired
    @PutMapping("/modify")
    public Object modifyTask(@RequestBody @Valid ModifyTaskForm modifyTaskForm){
        taskService.modifyTask(modifyTaskForm);
        return resultVOUtil.success("保存成功");
    }

    @LoginRequired
    @GetMapping("/list")
    public Object listTask(){
        List<Task> list = getUser().getTasks();
        return resultVOUtil.success(getUser().getTasks());
    }

    @LoginRequired
    @PostMapping("/finish")
    public Object finish(@RequestBody @Valid DeleteTaskForm deleteTaskForm){
        Integer taskId = deleteTaskForm.getTaskId();
        taskService.finish(taskId);
        return resultVOUtil.success("任务已完成");
    }
}
