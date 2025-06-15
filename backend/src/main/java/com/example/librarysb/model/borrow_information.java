package com.example.librarysb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Entity
@Table(name="borrow_table")
@Component
public class borrow_information {
    @Id
    @Column(name="borrow_id")
    private String borrow_id;

    @Column(name="book_id")
    private String book_id;
    @Column(name="borrower_id")
    private String borrower_id;
    @Column(name="borrow_time")
    private LocalDateTime borrow_time;
    @Column(name="due_return_time")
    private LocalDateTime due_return_time;
    @Column(name="actual_return_time")
    private LocalDateTime actual_return_time;

    public void setBorrow_id(String borrow_id)
    {
        this.borrow_id=borrow_id;
    }
    public String getBorrow_id()
    {
        return borrow_id;
    }
    public void setBook_id(String book_id)
    {
        this.book_id=book_id;
    }
    public String getBook_id()
    {
        return book_id;
    }
    public void setBorrower_id(String borrower_id){
        this.borrower_id=borrower_id;
    }
    public String getBorrower_id(){
        return borrower_id;
    }

    public void setBorrow_time(LocalDateTime borrow_time) {
        this.borrow_time = borrow_time;
    }

    public LocalDateTime getBorrow_time() {
        return borrow_time;
    }

    public void setDue_return_time(LocalDateTime due_return_time) {
        this.due_return_time = due_return_time;
    }

    public LocalDateTime getDue_return_time() {
        return due_return_time;
    }

    public void setActual_return_time(LocalDateTime actual_return_time) {
        this.actual_return_time = actual_return_time;
    }

    public LocalDateTime getActual_return_time() {
        return actual_return_time;
    }
}
