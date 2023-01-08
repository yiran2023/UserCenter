package com.yiran.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiran.usercenter.service.UserService;
import com.yiran.usercenter.model.domain.User;
import com.yiran.usercenter.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 29579
* @description 针对表【user(user table)】的数据库操作Service实现
* @createDate 2023-01-08 15:36:22
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




