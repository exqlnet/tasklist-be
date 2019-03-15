package com.ncuhome.tasklist.service;

import com.ncuhome.tasklist.dataobject.Task;
import com.ncuhome.tasklist.dataobject.User;
import com.ncuhome.tasklist.form.TaskForm.CreateTaskForm;
import com.ncuhome.tasklist.form.TaskForm.ModifyTaskForm;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.text.ParseException;
import java.util.List;

public interface TaskService {

    public String createTask(CreateTaskForm createTaskForm) throws ParseException;

    public Boolean modifyTask(Task task, ModifyTaskForm modifyTaskForm);

//    public Boolean finish(Integer taskId);

//    public Boolean unfinish(Integer taskId);

    public List<Task> getToday(User user);

//    public Boolean deleteTask(Task task);
    public Boolean isBTY(Task task);
}
