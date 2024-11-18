package org.example.book_manage.service;

import org.example.book_manage.pojo.Result;
import org.example.book_manage.pojo.User;

public interface UserService {
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);

    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    void register(String username, String password);

    /**
     * 更新用户信息
     * @param user
     */
    void update(User user);

    /**
     * 修改密码
     * @param id
     * @param password
     */
    void updatePassword(Integer id, String password);

    /**
     * 删除用户
     * @param id
     */
    void delete(Integer id);
}
