package com.ncuhome.tasklist.repository;

import com.ncuhome.tasklist.dataobject.EmailSend;
import com.ncuhome.tasklist.service.EmailService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailSendRepository extends JpaRepository<EmailSend, Integer> {
    EmailSend findByEmail(String email);
}
