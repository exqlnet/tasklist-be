package com.ncuhome.tasklist.repository;

import com.ncuhome.tasklist.dataobject.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

}
