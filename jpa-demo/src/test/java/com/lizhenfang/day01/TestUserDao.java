package com.lizhenfang.day01;

import com.lizhenfang.day01.Dao.UserDao;
import com.lizhenfang.day01.entity.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserDao {
    @Autowired
    private UserDao userDao;

    @Test
    public  void save(){
        User user= new User();
        user.setUsername("李振芳");
        user.setPassword("123");

        user.setCreateTime(new Date());
        User entity=userDao.save(user);
        System.out.println(user);

        //修改
       user.setUsername("张三");
       userDao.save(user);

    }

    @Test
    public  void delete(){
        //根据Id删除
        userDao.deleteById(4);
        userDao.deleteById(5);
        userDao.deleteById(6);


    }

    @Test
    public void findById(){
        User user = userDao.findById(7);
        System.out.println(user);
    }

    @Test
    public  void find(){
        //查询所有
       List<User> all= userDao.findAll();
       System.out.println(all);

    }


    @Test
    public  void findByUsername(){
        //模糊查询,需要添加“%”
        List<User> likeResult = userDao.findByUsername("张");
        System.out.println(likeResult);
    }

}
