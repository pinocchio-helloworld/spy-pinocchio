package com.example.librarysb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.librarysb"})
public class LibrarySbApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibrarySbApplication.class, args);
    }
}