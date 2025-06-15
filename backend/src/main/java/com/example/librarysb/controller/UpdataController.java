package com.example.librarysb.controller;

import com.example.librarysb.model.Book;
import com.example.librarysb.model.Information;
import com.example.librarysb.model.UserLogin;
import com.example.librarysb.model.borrow_information;
import com.example.librarysb.repository.BookRepository;
import com.example.librarysb.repository.HomepageRepository;
import com.example.librarysb.repository.UserLoginRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/update") // 修改基础路径为 /api/update
public class UpdataController {
    private static final Logger logger = LoggerFactory.getLogger(UpdataController.class);
    private final List<Book> books = new ArrayList<>();
    private String Userid;
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
    //还书的查找----------------------------------------------------------------------------------
    @GetMapping("/searchbook-re")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<Book> searchBooksre(@RequestParam String search,@RequestParam String getuser) {
        // 使用Optional包装可能为空的查询结果
        Optional<Book> bookOptional = bookRepository.findFirstByIdContainingOrNameContaining(search, search);


        Userid=getuser;
        //通过用户id找其记录
        Book book=bookOptional.get();
        String bookid=book.getId();
        if(book.getBorrowed()==true)
        {
           if(homepageRepository.existsWithActualReturnTimeNull(bookid,getuser)==false){
               book.setBorrowed(false);
               return ResponseEntity.ok(book);
           }
        }

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
    @Autowired
    private UserLoginRepository userLoginRepository;
    @Autowired
    private HomepageRepository homepageRepository;
    @Autowired
    private borrow_information borrow_information;
    @PostMapping("/borrowbook")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<String> borrowBook(@RequestBody Map<String, Object> request) {

        String bookId = (String) request.get("bookId"); // 与前端JSON字段名保持一致


        String getuser=(String) request.get("getuser");
        logger.info("接收到的用户ID: {}", getuser);
        UserLogin userLogin=userLoginRepository.findByUserid(getuser);
        Integer borrowNum = userLogin.getBorrownum();
        logger.info("接收到borrownum: {}", borrowNum);
        if (borrowNum == null || borrowNum >= 5) {
            return ResponseEntity.badRequest().body("借阅图书已达上限:5");
        }


        if (bookId == null || bookId.isEmpty()) {
            return ResponseEntity.badRequest().body("图书ID不能为空");
        }
        bookRepository.updateBorrowedStatusById(bookId, true);
        //userLoginRepository.incrementBorrowNumById(getuser);//有点问题无法加一


        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threeDaysLater = now.plusDays(3);
        borrow_information.setBook_id(bookId);
        borrow_information.setBorrower_id(getuser);
        borrow_information.setBorrow_time(now);
        borrow_information.setBorrow_id("111");
        borrow_information.setDue_return_time(threeDaysLater);
        homepageRepository.save(borrow_information);
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
        homepageRepository.updateActualReturnTime(bookId,Userid,LocalDateTime.now());
        return ResponseEntity.ok("还书成功");
    }
}
