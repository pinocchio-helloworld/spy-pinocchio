package com.example.librarysb.controller;

import com.example.librarysb.model.*;
import com.example.librarysb.repository.UserLoginRepository;
import com.example.librarysb.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin(origins = "http://localhost:8081") // 关键：允许前端域名跨域
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final LoginService loginService;
    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/api/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        logger.info("Received login request with userid: {}", loginRequest.getUserid());

        LoginResponse response = loginService.authenticate(loginRequest);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/api/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest registerrequest) {
        logger.info("Received register request with username: {}", registerrequest.getUsername());

        if(registerrequest.getUserType().equals("admin")&&!registerrequest.getAdminCode().equals("123456"))
            return ResponseEntity.ok(new ApiResponse(false, "注册失败：管理员密码不正确"));

        UserLogin userLogin=new UserLogin();
        userLogin.setUserid(registerrequest.getUsername());
        userLogin.setUserpassword(registerrequest.getPassword());
        userLogin.setUserrole(registerrequest.getUserType());
        userLoginRepository.save(userLogin);

        return ResponseEntity.ok(new ApiResponse(true,"注册成功,欢迎"+registerrequest.getUsername()));

    }

}