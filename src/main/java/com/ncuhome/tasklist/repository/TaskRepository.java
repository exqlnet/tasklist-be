package com.ncuhome.tasklist.repository;

import com.ncuhome.tasklist.dataobject.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    Task findByTaskId(Integer taskId);
}
