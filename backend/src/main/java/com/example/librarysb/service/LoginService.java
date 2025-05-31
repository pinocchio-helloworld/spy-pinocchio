package com.example.librarysb.service;

import com.example.librarysb.model.LoginRequest;
import com.example.librarysb.model.LoginResponse;
import com.example.librarysb.model.UserLogin;
import com.example.librarysb.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserLoginRepository userLoginRepository;

    public LoginResponse authenticate(LoginRequest loginRequest) {

        String userid = loginRequest.getUserid();
        String password = loginRequest.getPassword();

        UserLogin userLogin = userLoginRepository.findByUserid(userid);

        if (userLogin != null && userLogin.getUserpassword().equals(password)) {
            return new LoginResponse(true, "登录成功",userLogin.getUserrole());
        } else {
            return new LoginResponse(false, "用户名或密码错误",userLogin.getUserrole());
        }
    }
}