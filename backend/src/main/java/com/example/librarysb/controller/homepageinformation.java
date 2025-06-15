package com.example.librarysb.controller;

import com.example.librarysb.model.Information;
import com.example.librarysb.repository.BookRepository;
import com.example.librarysb.repository.HomepageRepository;
import com.example.librarysb.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class homepageinformation {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    private HomepageRepository homepageRepository;

    @GetMapping("/api/information")
    public Information getInformation() {
        Information info = new Information();
        info.setBookcounts((int) bookRepository.count());
        info.setUsercounts((int) userLoginRepository.count()); // 包括管理员
        info.setBorrowcounts((int) homepageRepository.countTodayBorrows());
        info.setReturncounts((int) homepageRepository.countTodayReturns());
        return info;
    }
}