package org.example.book_manage.controller;

import org.example.book_manage.pojo.Result;
import org.example.book_manage.pojo.User;
import org.example.book_manage.service.UserService;
import org.example.book_manage.utils.JwtUtil;
import org.example.book_manage.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Result<String> login(@RequestParam String username, @RequestParam String password) {
        if (username==null||password==null){
            return Result.error("用户名或密码为空");
        }
        User existingUser =userService.login(username, password);
        if (Md5Util.getMD5String(password).equals(existingUser.getPassword())) {
            final Map<String, Object> map = Map.of("id", existingUser.getId(),
                    "username", existingUser.getUsername());
            final String token = JwtUtil.genToken(map);
            // Save token to redis
            final ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            // If key is token, and when we validate, and the token doesn't exist, then it has expired.
            operations.set(token, token, Duration.ofHours(12));
            // Return JWT token
            return Result.success(token);
        } else {
            return Result.error("密码错误");
        }
        return Result.success();
    }

    @PostMapping("/register")
    public Result<String> register( String username, String password) {
        if (username==null||password==null){
            return Result.error("用户名或密码为空");
        }
        userService.register(username, password);
        return Result.success();
    }

    @PutMapping
    public Result<String> update(@RequestBody User user) {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updatepassword/{id}")
    public Result<String> updatepassword( @PathVariable Integer id, @RequestBody String password) {
        userService.updatePassword(id, password);
        return Result.success();
    }

    @DeleteMapping
    public Result<String> delete(Integer id) {
        userService.delete(id);
        return Result.success();
    }

}
