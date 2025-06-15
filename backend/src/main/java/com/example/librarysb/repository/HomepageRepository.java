package com.example.librarysb.repository;

import com.example.librarysb.model.borrow_information;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface HomepageRepository extends JpaRepository<borrow_information, String> {
    @Query("SELECT COUNT(b) FROM borrow_information b WHERE " +
            "b.borrow_time >= :#{T(java.time.LocalDate).now().atStartOfDay()} AND " +
            "b.borrow_time < :#{T(java.time.LocalDate).now().plusDays(1).atStartOfDay()}")
    long countTodayBorrows();

    @Query("SELECT COUNT(b) FROM borrow_information b WHERE " +
            "b.actual_return_time >= :#{T(java.time.LocalDate).now().atStartOfDay()} AND " +
            "b.actual_return_time < :#{T(java.time.LocalDate).now().plusDays(1).atStartOfDay()}")
    long countTodayReturns();

    //borrow_information findByBook_id(String Book_id);

    @Query("SELECT COUNT(bi) > 0 FROM borrow_information bi WHERE bi.book_id = :bookId AND bi.borrower_id = :userId AND bi.actual_return_time IS NULL")
    boolean existsWithActualReturnTimeNull(@Param("bookId") String bookId, @Param("userId") String userId);

    @Modifying
    @Transactional
    @Query("UPDATE borrow_information bi SET bi.actual_return_time = :currentTime WHERE bi.book_id = :bookId AND bi.borrower_id = :userId AND bi.actual_return_time IS NULL")
    int updateActualReturnTime(@Param("bookId") String bookId, @Param("userId") String userId, @Param("currentTime") LocalDateTime currentTime);

}