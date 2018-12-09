package com.ncuhome.tasklist.service;

import com.ncuhome.tasklist.dataobject.User;
import com.ncuhome.tasklist.form.TaskForm.CreateTaskForm;
import com.ncuhome.tasklist.form.TaskForm.ModifyTaskForm;

import java.text.ParseException;

public interface TaskService {

    public String createTask(CreateTaskForm createTaskForm) throws ParseException;

    public String deleteTask(Integer taskId);

    public String modifyTask(ModifyTaskForm modifyTaskForm);

    public Boolean finish(Integer taskId);
}
