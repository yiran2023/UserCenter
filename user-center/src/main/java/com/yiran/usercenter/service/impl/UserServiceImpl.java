package com.yiran.usercenter.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiran.usercenter.service.UserService;
import com.yiran.usercenter.model.domain.User;
import com.yiran.usercenter.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author Yiran
 * user service implementation
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Resource
    private UserMapper userMapper;

    // passward salt
    private static final String SALT = "yiran";

    // user login status string
    private static final String USER_LOGIN_STATE = "userLoginState";

    @Override
    public long userRegister(String userAccount, String userPassward, String checkPassward) {
        // check whether user-typed account/passward is null
        if (StringUtils.isAnyBlank(userAccount, userPassward, checkPassward)) {
            return -1;
        }
        // user account length should be greater than 4
        if (userAccount.length() < 4) {
            return -1;
        }
        // user passward should be greater than 8
        if (userPassward.length() < 8 || checkPassward.length() < 8) {
            return -1;
        }
        // check whether the account has any special(invalid) characters
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return -1;
        }
        // check whether the passward equals checkPassward
        if (!userPassward.equals(checkPassward)) {
            return -1;
        }
        // if the account has already been used (exist in the database), return -1 to denote cannot be use;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            return -1;
        }
        // encode the passward and store it in the database
        String encryptPassward = DigestUtils.md5DigestAsHex((SALT + userPassward).getBytes());
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassward(encryptPassward);
        boolean saveResult = this.save(user);
        if (!saveResult) {
            return -1;
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassward, HttpServletRequest request) {
        // check whether user-typed account/passward is null
        if (StringUtils.isAnyBlank(userAccount, userPassward)) {
            return null;
        }
        // user account length should be greater than 4
        if (userAccount.length() < 4) {
            return null;
        }
        // user passward should be greater than 8
        if (userPassward.length() < 8) {
            return null;
        }
        // check whether the account has any special(invalid) characters
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return null;
        }
        // encode the passward and store it in the database
        String encryptPassward = DigestUtils.md5DigestAsHex((SALT + userPassward).getBytes());
        // check if user exist
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassward", encryptPassward);
        User user = userMapper.selectOne(queryWrapper);
        // user does not exist
        if (user == null) {
            log.info("user login failed, user account / passward does not match");
            return null;
        }
        // protect user's information
        User safetyUser = new User();
        safetyUser.setId(user.getId());
        safetyUser.setUsername(user.getUsername());
        safetyUser.setUserAccount(user.getUserAccount());
        safetyUser.setAvatarUrl(user.getAvatarUrl());
        safetyUser.setGender(user.getGender());
        safetyUser.setPhone(user.getPhone());
        safetyUser.setEmail(user.getEmail());
        safetyUser.setUserStatus(user.getUserStatus());
        safetyUser.setCreateTime(user.getCreateTime());
        safetyUser.setIsDelete(0);
        // store user's login status to session
        request.getSession().setAttribute(USER_LOGIN_STATE, user);
        return user;
    }
}




