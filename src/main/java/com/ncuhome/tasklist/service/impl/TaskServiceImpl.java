package com.ncuhome.tasklist.service.impl;

import com.ncuhome.tasklist.dataobject.Task;
import com.ncuhome.tasklist.dataobject.User;
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
        return null;
    }

    @Override
    public String modifyTask(ModifyTaskForm modifyTaskForm) {
        return null;
    }
}
