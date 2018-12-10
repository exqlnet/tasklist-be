package com.ncuhome.tasklist.repository;

import com.ncuhome.tasklist.dataobject.Task;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    Task findByTaskId(Integer taskId);

    @Query(value = "select * from task where user_id=?1 and date(start_time)= curdate()", nativeQuery = true)
    List<Task> getTodayTask(Integer userId);
}
