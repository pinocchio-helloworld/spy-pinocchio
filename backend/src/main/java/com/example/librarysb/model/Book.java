package com.example.librarysb.model;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Data
@Entity
@Table(name = "books") // 指定数据库表名为 "books"
public class Book {
    @Id
    @Column(name = "book_id") // 指定数据库列名为 "book_id"
    private String id;

    @Column(name = "title") // 指定数据库列名为 "title"
    private String name;

    @Column(name = "author") // 指定数据库列名为 "author"
    private String author;

    @Column(name = "genre") // 指定数据库列名为 "type"
    private String type;

    @Column(name = "room_id") // 指定数据库列名为 "room"
    private String room;

    @Column(name = "is_borrowed") // 指定数据库列名为 "is_borrowed"
    private boolean borrowed;

    public String getId(){ return id;}
    public void setId(String id){this.id=id;}

    public String getName(){ return name;}
    public void setName(String name){this.name=name;}

    public String getAuthor(){ return author;}
    public void setAuthor(String author){this.author=author;}


    public String getType(){ return type;}
    public void setType(String type){this.type=type;}

    public String getRoom(){ return room;}
    public void setRoom(String room){this.room=room;}


    public boolean getBorrowed(){ return borrowed;}
    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

}