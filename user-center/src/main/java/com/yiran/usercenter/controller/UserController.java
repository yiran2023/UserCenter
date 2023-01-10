package com.yiran.usercenter.controller;

import com.yiran.usercenter.model.domain.User;
import com.yiran.usercenter.model.request.UserLoginRequest;
import com.yiran.usercenter.model.request.UserRegisterRequest;
import com.yiran.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * user controller
 *
 * @author Yiran
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassward = userRegisterRequest.getUserPassward();
        String checkPassward = userRegisterRequest.getCheckPassward();
        if (StringUtils.isAnyBlank(userAccount, userPassward, checkPassward)) {
            return null;
        }
        long id = userService.userRegister(userAccount, userPassward, checkPassward);
        return id;
    }

    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassward = userLoginRequest.getUserPassward();
        if (StringUtils.isAnyBlank(userAccount, userPassward)) {
            return null;
        }
        return userService.userLogin(userAccount, userPassward, request);
    }
}
