package com.ncuhome.tasklist.service.impl;

import com.mysql.cj.MysqlxSession;
import com.mysql.cj.NativeSession;
import com.mysql.cj.Session;
import com.ncuhome.tasklist.dataobject.Task;
import com.ncuhome.tasklist.dataobject.User;
import com.ncuhome.tasklist.enums.TaskTypeEnum;
import com.ncuhome.tasklist.exception.TaskException;
import com.ncuhome.tasklist.form.TaskForm.CreateTaskForm;
import com.ncuhome.tasklist.form.TaskForm.ModifyTaskForm;
import com.ncuhome.tasklist.repository.TaskRepository;
import com.ncuhome.tasklist.repository.UserRepository;
import com.ncuhome.tasklist.service.TaskService;
import com.ncuhome.tasklist.service.UserProvider;
import com.ncuhome.tasklist.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private EntityManager entityManager;

    @Override
    public String createTask(CreateTaskForm createTaskForm) throws ParseException{

        Task task = new Task();
        task.setTitle(createTaskForm.getTitle());
        task.setLabel(createTaskForm.getLabel());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        String wtfString = createTaskForm.getStartDate().replaceAll("T.+","-" + createTaskForm.getStartTime());
        System.out.println(wtfString); // out string
        log.info("{}", simpleDateFormat.parse(wtfString));
        task.setStartTime(simpleDateFormat.parse(wtfString));
        log.info("{}", task.getStartTime());
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

    @Override
    public Boolean unfinish(Integer taskId){
        Task task = taskRepository.findByTaskId(taskId);
        if(task == null){
            throw new TaskException("任务不存在");
        }
        User user = (User)request.getAttribute("user");
        if(!task.getUser().getUserId().equals(user.getUserId())){
            throw new TaskException("无法修改该任务");
        }

        task.setIsFinish(0);
        taskRepository.save(task);
        return true;
    }

    @Override
    public List<Task> getToday(User user) {
        List<Task> tasks = taskRepository.getTodayTask(user.getUserId());
        return tasks;
    }
}
