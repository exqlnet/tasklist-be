package com.ncuhome.tasklist.repository;

import com.ncuhome.tasklist.dataobject.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
