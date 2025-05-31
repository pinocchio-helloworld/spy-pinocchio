package com.example.librarysb.controller;

import com.example.librarysb.model.Book;
import com.example.librarysb.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    // 假设你有一个服务类来处理数据库操作
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 使用 @CrossOrigin 注解配置 CORS
    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping
    public List<Book> getAllBooks() {
        logger.info("Received request to fetch all books");
        List<Book> books = bookService.findAllBooks();
        logger.info("Returning {} books id={}", books.size(),books.get(0).getId());
        return books;
    }
}