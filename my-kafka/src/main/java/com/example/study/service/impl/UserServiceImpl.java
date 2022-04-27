package com.example.study.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.study.model.entity.UserEntity;
import com.example.study.mapper.UserMapper;
import com.example.study.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author 154594742@qq.com
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
}
