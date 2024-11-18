package org.example.book_manage.pojo;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class BorrowRecord {
    private int id;
    private int userId;
    private int bookId;
    private LocalDateTime borrowDate;
    private  LocalDateTime returnDate;
}

