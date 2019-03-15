package com.ncuhome.tasklist.service.impl;

import com.ncuhome.tasklist.dataobject.Task;
import com.ncuhome.tasklist.dataobject.User;
import com.ncuhome.tasklist.enums.TaskTypeEnum;
import com.ncuhome.tasklist.form.TaskForm.CreateTaskForm;
import com.ncuhome.tasklist.form.TaskForm.ModifyTaskForm;
import com.ncuhome.tasklist.repository.TaskRepository;
import com.ncuhome.tasklist.repository.UserRepository;
import com.ncuhome.tasklist.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final static Map MAP = new HashMap<String, Integer>();

    @Autowired
    private UserRepository userRepository;

//
//    @Autowired
//    private UserProvider userProvider;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private User currentUser;

    @Override
    public String createTask(CreateTaskForm createTaskForm) throws ParseException{

        Task task = new Task();
        task.setTitle(createTaskForm.getTitle());
        task.setLabel(createTaskForm.getLabel());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        String wtfString = createTaskForm.getStartDate().replaceAll("T.+","-" + createTaskForm.getStartTime());
        Date startTime = simpleDateFormat.parse(wtfString);
        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        c.add(Calendar.DAY_OF_MONTH, 1);
        startTime = c.getTime();
        task.setStartTime(startTime);

        task.setType(TaskTypeEnum.StringToInteger(createTaskForm.getType()));
        task.setDescription(createTaskForm.getDescription());
        task.setIsFinish(0);
        task.setPriority(createTaskForm.getPriority());

//        task.setStartTime();
//        task.setStartTime(createTaskForm.getStartTime());
        task.setUser((User)request.getAttribute("user"));
        taskRepository.save(task);
        return "添加成功";
    }


    @Override
    public Boolean modifyTask(Task task, ModifyTaskForm modifyTaskForm) {
        task.setStartTime(modifyTaskForm.getStartTime());
        task.setDescription(modifyTaskForm.getDescription());
        task.setPriority(modifyTaskForm.getPriority());
        task.setLabel(modifyTaskForm.getLabel());
        task.setTitle(modifyTaskForm.getTitle());
        taskRepository.save(task);

        return true;
    }


    @Override
    public List<Task> getToday(User user) {
        List<Task> tasks = taskRepository.getTodayTask(user.getUserId());
        return tasks;
    }

    public Boolean isBTY(Task task){
        return currentUser.getUserId().equals(task.getUser().getUserId());
    }
}
