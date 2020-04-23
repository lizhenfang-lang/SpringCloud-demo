package com.liizhenfang.day01.controller;


import com.liizhenfang.day01.service.UserService;
import com.lizhenfang.day01.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 根据Id查询用户
     * @param id
     * @return
     */
    @RequestMapping("getUserById")
    public User getUserById(@RequestParam("id") Integer id){
        log.info("userId:{}",id);
        return userService.getUserById(id);
    }

    @RequestMapping("getUserByUser")
    public User getUserByUser(@RequestBody User user){
        log.info("userId:{}",user.getId());
       /* try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }*/
        return userService.getUserById(user.getId());
    }

    /**
     * 根据Id，查询用户名称
     * @param id
     * @return
     */
    @RequestMapping("getUsernameById")
    public String getUsernameById(@RequestParam("id") Integer id){
        return userService.getUsernameById(id);
    }
}
