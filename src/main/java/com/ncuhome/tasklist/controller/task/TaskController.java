package com.ncuhome.tasklist.controller.task;


import com.google.gson.JsonObject;
import com.ncuhome.tasklist.VO.TaskVO;
import com.ncuhome.tasklist.annotations.LoginRequired;
import com.ncuhome.tasklist.dataobject.Task;
import com.ncuhome.tasklist.dataobject.User;
import com.ncuhome.tasklist.enums.HttpEnum;
import com.ncuhome.tasklist.form.TaskForm.CreateTaskForm;
import com.ncuhome.tasklist.form.TaskForm.DeleteTaskForm;
import com.ncuhome.tasklist.form.TaskForm.ModifyTaskForm;
import com.ncuhome.tasklist.repository.TaskRepository;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    private User currentUser;

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

        // 验证Task是否属于该用户
        Task task = taskRepository.findByTaskId(taskId);
        if(!task.getUser().getUserId().equals(currentUser.getUserId()))
            return resultVOUtil.fromEnum(HttpEnum.TASK_NOT_BTY);

        taskRepository.delete(task);
        return resultVOUtil.success("删除成功");
    }

    @LoginRequired
    @PutMapping("/modify")
    public Object modifyTask(@RequestBody @Valid ModifyTaskForm modifyTaskForm){
        Task task = taskRepository.findByTaskId(modifyTaskForm.getTaskId());
        if(task == null){
            return resultVOUtil.fromEnum(HttpEnum.TASK_NOT_FOUND);
        }

        // 检查是否属于该用户
        if(currentUser.getUserId().equals(task.getUser().getUserId())){
            return resultVOUtil.fromEnum(HttpEnum.TASK_NOT_BTY);
        }
        taskService.modifyTask(task, modifyTaskForm);
        return resultVOUtil.success("修改成功");
    }

    @GetMapping("/list")
    @LoginRequired
    public Object listTask(){
        Map<String, List> data = new HashMap<>();
        data.put("finish", new ArrayList<TaskVO>());
        data.put("unfinish", new ArrayList<TaskVO>());
        for(Task task : currentUser.getTasks()){
            if(task.getIsFinish().equals(1)){
                data.get("finish").add(new TaskVO(task));
            }
            else{
                data.get("unfinish").add(new TaskVO(task));
            }
        }
        return resultVOUtil.success(data);
    }

    @LoginRequired
    @PostMapping("/finish")
    public Object finish(@RequestBody @Valid DeleteTaskForm deleteTaskForm){
        Integer taskId = deleteTaskForm.getTaskId();
        Task task = taskRepository.findByTaskId(taskId);
        if(task == null){
            return resultVOUtil.fromEnum(HttpEnum.TASK_NOT_FOUND);
        }
        if(!taskService.isBTY(task)){
            return resultVOUtil.fromEnum(HttpEnum.TASK_NOT_BTY);
        }

        task.setIsFinish(1);
        taskRepository.save(task);
        return resultVOUtil.success("任务已完成");
    }

    @LoginRequired
    @PostMapping("/unfinish")
    public Object unfinish(@RequestBody @Valid DeleteTaskForm deleteTaskForm){
        Integer taskId = deleteTaskForm.getTaskId();
        Task task = taskRepository.findByTaskId(taskId);
        if(task == null){
            return resultVOUtil.fromEnum(HttpEnum.TASK_NOT_FOUND);
        }
        if(!taskService.isBTY(task)){
            return resultVOUtil.fromEnum(HttpEnum.TASK_NOT_BTY);
        }
        task.setIsFinish(0);
        taskRepository.save(task);
        return resultVOUtil.success("取消完成");
    }

    @LoginRequired
    @GetMapping("/today")
    public Object getTodayTask(){
        return resultVOUtil.success(taskService.getToday(currentUser));
    }
}
