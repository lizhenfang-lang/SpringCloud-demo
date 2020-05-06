package com.lizhenfang.day01;

import com.lizhenfang.day01.dao.OptionLogDao;
import com.lizhenfang.day01.dao.UserDao;
import com.lizhenfang.day01.model.OptionLog;
import com.lizhenfang.day01.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private OptionLogDao optionLogDao;

    /** 新增、修改 **/
    @Test
    public void save(){
        User user = new User();
        user.setId("2");
        user.setUsername("范冰冰");
        user.setUserType(2);
        user.setCreated(new Date());
        //新增或修改
       // User save= userDao.save(user);
        User save=mongoTemplate.save(user);
        System.out.println(save);
    }
    /** 查询 **/
    @Test
    public void query(){
/** id查询 **/
        //User user = userDao.findById("100").get();
       // System.out.println(user);
/** 分页查询 **/
        Pageable pageable = PageRequest.of(0,10);
        Page<User> all = userDao.findAll(pageable);
        System.out.println(all.getContent());
/** 自定义查询 **/
       //User findbyUsername = userDao.findByUsername("李振芳");
      // System.out.println(findbyUsername);

        User fingById= mongoTemplate.findById("101",User.class,"user");
        System.out.println(fingById);
    }
    /** 删除 **/
    @Test
    public void del(){
        userDao.deleteById("101");
    }

    @Test
    public void  logTest(){
        OptionLog optionLog = new OptionLog();
        optionLog.setId(UUID.randomUUID().toString());
        optionLog.setExeLog("throw info");
        optionLog.setMethodName("addLog");
        optionLog.setParameter("{id:1,methodName:\"addLog\"}");
        optionLog.setReturnValve("{result:true}");
        optionLogDao.save(optionLog);
    }
}
