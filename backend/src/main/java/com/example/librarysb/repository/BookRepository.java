package com.example.librarysb.repository;

import com.example.librarysb.model.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> {
    Optional<Book> findFirstByIdContainingOrNameContaining(String search, String search1);

        // 使用 JPQL 更新语句（推荐）
        @Transactional // 必须添加事务注解
        @Modifying // 标识为修改操作
        @Query("UPDATE Book SET borrowed = :isBorrowed WHERE id = :bookId")
        void updateBorrowedStatusById(@Param("bookId") String id, @Param("isBorrowed") boolean isBorrowed);
    }

