package org.example.book_manage.pojo;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String role = "user"; // 默认值为 'user'
}

