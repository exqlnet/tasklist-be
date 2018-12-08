package com.ncuhome.tasklist.service.impl;

import com.ncuhome.tasklist.dataobject.Task;
import com.ncuhome.tasklist.dataobject.User;
import com.ncuhome.tasklist.exception.TaskException;
import com.ncuhome.tasklist.form.TaskForm.CreateTaskForm;
import com.ncuhome.tasklist.form.TaskForm.ModifyTaskForm;
import com.ncuhome.tasklist.repository.TaskRepository;
import com.ncuhome.tasklist.repository.UserRepository;
import com.ncuhome.tasklist.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private HttpServletRequest request;

    @Override
    public String createTask(CreateTaskForm createTaskForm) {
        Task task = new Task(createTaskForm);
        task.setUser((User)request.getAttribute("user"));
        taskRepository.save(task);
        return "添加成功";
    }

    @Override
    public String deleteTask(Integer taskId) {
        Task task = taskRepository.findByTaskId(taskId);
        if(task == null){
            throw new TaskException("未找到该任务");
        }
        User user = (User)request.getAttribute("user");
        if(!task.getUser().getUserId().equals(user.getUserId())){
            throw new TaskException("该任务不属于你");
        };
        taskRepository.delete(task);
        return "删除成功";
    }

    @Override
    public String modifyTask(ModifyTaskForm modifyTaskForm) {
        Task task = taskRepository.findByTaskId(modifyTaskForm.getTaskId());
        if(task == null){
            throw new TaskException("未找到该任务");
        }
        User user = (User)request.getAttribute("user");
        if(!task.getUser().getUserId().equals(user.getUserId())){
            throw new TaskException("该任务不属于你");
        };

        task.setStartTime(modifyTaskForm.getStartTime());
        task.setDescription(modifyTaskForm.getDescription());
        task.setPriority(modifyTaskForm.getPriority());
        task.setLabel(modifyTaskForm.getLabel());
        task.setTitle(modifyTaskForm.getTitle());
        taskRepository.save(task);

        return "保存成功";
    }

    @Override
    public Boolean finish(Integer taskId) {
        Task task = taskRepository.findByTaskId(taskId);
        if(task == null){
            throw new TaskException("任务不存在");
        }
        User user = (User)request.getAttribute("user");
        if(!task.getUser().getUserId().equals(user.getUserId())){
            throw new TaskException("无法修改该任务");
        }

        task.setIsFinish(1);
        taskRepository.save(task);
        return true;
    }
}
