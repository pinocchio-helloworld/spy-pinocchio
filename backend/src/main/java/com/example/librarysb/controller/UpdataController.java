package com.example.librarysb.controller;

import com.example.librarysb.model.Book;
import com.example.librarysb.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/update") // 修改基础路径为 /api/update
public class UpdataController {
    private final List<Book> books = new ArrayList<>();
    @Autowired
    private BookRepository bookRepository;

    // 添加图书接口
    @PostMapping("/addbook") // 添加图书的路径变为 /api/update/addbook
    @CrossOrigin(origins = "http://localhost:8081") // 允许来自 http://localhost:8081 的请求
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        // 模拟添加图书到数据库
        bookRepository.save(book);
        return ResponseEntity.ok("图书添加成功！");
    }

    // 搜索图书接口
    @GetMapping("/searchbook")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<Book> searchBooks(@RequestParam String search) {
        // 使用Optional包装可能为空的查询结果
        Optional<Book> bookOptional = bookRepository.findFirstByIdContainingOrNameContaining(search, search);

        // 返回200 OK（有数据）或404 Not Found（无数据）
        return bookOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    //deletebook-------------------------------------------------------------------
    @DeleteMapping("/deletebook/{id}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<String> deleteBook(@PathVariable String id) {
        try {
            bookRepository.deleteById(id);
            return ResponseEntity.ok("图书删除成功");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("图书删除失败: " + e.getMessage());
        }
    }

    //借书borrowbook
    @PostMapping("/borrowbook")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<String> borrowBook(@RequestBody Map<String, Object> request) {
        String bookId = (String) request.get("bookId"); // 与前端JSON字段名保持一致
        if (bookId == null || bookId.isEmpty()) {
            return ResponseEntity.badRequest().body("图书ID不能为空");
        }
        bookRepository.updateBorrowedStatusById(bookId, true);
        return ResponseEntity.ok("借阅成功");
    }
    // 还书接口
    @PostMapping("/returnbook") // 还书的路径变为 /api/update/returnbook
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<String> returnBook(@RequestBody Map<String, Object> request) {
        String bookId = (String) request.get("bookId");
        if (bookId == null || bookId.isEmpty()) {
            return ResponseEntity.badRequest().body("图书ID不能为空");
        }
        bookRepository.updateBorrowedStatusById(bookId, false);
        return ResponseEntity.ok("借阅成功");
    }
}
