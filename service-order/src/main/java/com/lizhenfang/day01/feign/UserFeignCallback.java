package com.lizhenfang.day01.feign;

import com.lizhenfang.day01.User;
import org.springframework.stereotype.Component;

@Component
public class UserFeignCallback implements UserFeignClient {

    @Override
    public User getUserById(Integer id) {
        User u= new User();
        u.setUsername("固定值");
        return u;
    }

    @Override
    public User getUserByUser(User user) {
       User u= new User();
       u.setUsername("固定值");
       return u;
    }

    @Override
    public String getUsernameById(Integer id) {
        return null;
    }
}
