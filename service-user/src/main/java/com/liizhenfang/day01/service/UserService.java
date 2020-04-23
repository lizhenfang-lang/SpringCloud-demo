package com.liizhenfang.day01.service;

import com.liizhenfang.day01.mapper.UserMapper;
import com.lizhenfang.day01.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    UserMapper userMapper;
    /**
     * 根据Id，查询user
     * @param id
     * @return
     */
    public User getUserById(Integer id){

        return  userMapper.getUserById(id);
    }

    /**
     * 根据Id，查询用户名称
     * @param id
     * @return
     */
    public String getUsernameById(Integer id){
        User user = getUserById(id);
        return user.getUsername();
    }
}