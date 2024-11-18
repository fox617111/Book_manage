package org.example.book_manage.pojo;

import lombok.Data;

@Data
public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private String status = "available"; // 默认值为 'available'
}
