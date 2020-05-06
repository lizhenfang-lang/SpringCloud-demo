package com.lizhenfang.day01.dao;

import com.lizhenfang.day01.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDao extends MongoRepository<User,String> {
    /** 自定义接口 **/
    User findByUsername(String username);
}
