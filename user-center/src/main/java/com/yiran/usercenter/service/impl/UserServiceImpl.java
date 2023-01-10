package com.yiran.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiran.usercenter.service.UserService;
import com.yiran.usercenter.model.domain.User;
import com.yiran.usercenter.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author Yiran
 * user service implementation
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Resource
    private UserMapper userMapper;

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
        final String SALT = "yiran";
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
}




