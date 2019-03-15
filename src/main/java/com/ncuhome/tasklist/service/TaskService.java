package com.ncuhome.tasklist.service;

import com.ncuhome.tasklist.dataobject.Task;
import com.ncuhome.tasklist.dataobject.User;
import com.ncuhome.tasklist.form.TaskForm.CreateTaskForm;
import com.ncuhome.tasklist.form.TaskForm.ModifyTaskForm;

import java.text.ParseException;
import java.util.List;

public interface TaskService {

    public String createTask(CreateTaskForm createTaskForm) throws ParseException;

    public String modifyTask(ModifyTaskForm modifyTaskForm);

    public Boolean finish(Integer taskId);

    public Boolean unfinish(Integer taskId);

    public List<Task> getToday(User user);

}
