package com.yiran.usercenter.service;
import java.util.Date;

import com.yiran.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * user table test
 *
 * @author  Yiran
 */
@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testAddUser() {
        User user = new User();
        user.setId(0L);
        user.setUsername("yiran1");
        user.setUserAccount("123");
        user.setAvatarUrl("https://codenav-1256524210.cos.ap-shanghai.myqcloud.com/user_avatar/1610518142000300034/YeedIoq3-logo.png");
        user.setGender(0);
        user.setUserPassward("xxx");
        user.setPhone("123");
        user.setEmail("456");
        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);
    }
}