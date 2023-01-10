package com.yiran.usercenter.service;

import com.yiran.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author Yiran
*
 * user service
*/
public interface UserService extends IService<User> {

    /**
     * user register function
     *
     * @param userAccount   user-typed registeration account
     * @param userPassward  user-typed registration passward
     * @param checkPassward user double check passward
     * @return new user id
     */
    long userRegister(String userAccount, String userPassward, String checkPassward);

    /**
     * user login function
     *
     * @param userAccount   user-typed registeration account
     * @param userPassward  user-typed registration passward
     * @param request   session that store user information
     * @return    user information
     */
    User userLogin(String userAccount, String userPassward, HttpServletRequest request);
}
