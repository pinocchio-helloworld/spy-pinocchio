package com.example.librarysb.repository;

import com.example.librarysb.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginRepository extends JpaRepository<UserLogin, String> {
    UserLogin findByUserid(String userid);
}