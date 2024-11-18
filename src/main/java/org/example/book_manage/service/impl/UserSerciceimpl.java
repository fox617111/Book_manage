package org.example.book_manage.service.impl;

import org.example.book_manage.mapper.UserMapper;
import org.example.book_manage.pojo.Result;
import org.example.book_manage.pojo.User;
import org.example.book_manage.service.UserService;
import org.example.book_manage.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSerciceimpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserSerciceimpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User login(String username, String password) {
        String md5String = Md5Util.getMD5String(password);
        User user =userMapper.SelectUser(username, md5String);
        return user;
    }

    @Override
    public void register(String username, String password) {
        String md5String = Md5Util.getMD5String(password);
        userMapper.InsertUser(username, md5String);

    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void updatePassword(Integer id, String password) {
        String newpassword = Md5Util.getMD5String(password);
        userMapper.updatePassword(id, newpassword);
    }

    @Override
    public void delete(Integer id) {
        userMapper.delete(id);
    }
}
