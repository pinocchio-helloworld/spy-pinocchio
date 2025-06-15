package com.example.librarysb.repository;

import com.example.librarysb.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserLoginRepository extends JpaRepository<UserLogin, String> {
    UserLogin findByUserid(String userid);

    @Query("SELECT u.borrownum FROM UserLogin u WHERE u.userid = ?1")
    Integer findBorrowNumById(String id);

    @Modifying
    @Query("UPDATE UserLogin u SET u.borrownum = u.borrownum + 1 WHERE u.userid = :userid")
    void incrementBorrowNumById(@Param("userid") String userid);


}