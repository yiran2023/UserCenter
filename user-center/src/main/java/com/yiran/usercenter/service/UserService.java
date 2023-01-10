package com.yiran.usercenter.service;

import com.yiran.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Yiran
*
 * user service
*/
public interface UserService extends IService<User> {

    /**
     * @param userAccount   user-typed registeration account
     * @param userPassward  user-typed registration passward
     * @param checkPassward user double check passward
     * @return new user id
     */
    long userRegister(String userAccount, String userPassward, String checkPassward);
}
