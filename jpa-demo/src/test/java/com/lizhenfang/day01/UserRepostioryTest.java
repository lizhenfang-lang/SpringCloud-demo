package com.lizhenfang.day01;

import com.lizhenfang.day01.Dao.UserRepository;
import com.lizhenfang.day01.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepostioryTest {

    @Resource
    private UserRepository userRepository;

    //添加
    @Test
    public void save(){

        User userEntity = new User();
        userEntity.setUsername("ｗａｎｇｗｕ");
        userEntity.setPassword("123456");
        userEntity.setCreateTime(new Date());

        userRepository.save(userEntity);
        System.out.println("添加成功");

    }

    //修改
    @Test
    public void update(){

        //现根据id查询其对象
        User userEntity = userRepository.findById(7).get();
        userEntity.setUsername("666666");
        userRepository.save(userEntity);
        System.out.println("修改成功");

    }

    //根据id查询
    @Test
    public  void findAll(){
        Iterable<User> all = userRepository.findAll();
        for (User user:all
        ) {
            System.out.println(user);
        }
    }

    //根据多个id查询
    @Test
    public void findAllById(){
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(3);
        ids.add(5);
        Iterable<User> allById = userRepository.findAllById(ids);
        for (User user:allById
        ) {
            System.out.println(user);
        }
    }

    //多个添加
    @Test
    public  void saveAll(){

        User userEntity = new User();
        userEntity.setUsername("zhaoliu");
        userEntity.setPassword("123456");
        userEntity.setCreateTime(new Date());


        User userEntity1 = new User();
        userEntity1.setUsername("sunqi");
        userEntity1.setPassword("123456");
        userEntity1.setCreateTime(new Date());


        ArrayList<User> userEntities = new ArrayList<>();

        userEntities.add(userEntity);
        userEntities.add(userEntity1);
        Iterable<User> userEntities1 = userRepository.saveAll(userEntities);
        System.out.println(userEntities1);
        System.out.println("添加成功");
    }


    //批量删除
    @Test
    public  void deleteAll(){
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(3);
        ids.add(5);
        Iterable<User> allById = userRepository.findAllById(ids);

        userRepository.deleteAll(allById);
        System.out.println("删除成功");

    }
    //排序
    @Test
    public void sort(){
        //一个排序条件
        Sort sort = Sort.by(Sort.Direction.ASC,"username");
        Iterable<User> all = userRepository.findAll(sort);
        System.out.println(all);
        //多个字段排序
        Sort.Order usernameOrder = Sort.Order.desc("username");
        Sort.Order idOrder = Sort.Order.desc("id");
        Sort sort1 = Sort.by(usernameOrder, idOrder);
        Iterable<User> all1 = userRepository.findAll(sort1);
        System.out.println(all1);
    }
    //排序+分页
    @Test
    public void  pageAndSorting(){
        //用PageRequest构建分页的对象，page开始页数，它从0开始的，0代表第一页。
        Pageable pageable = PageRequest.of(0,2);
        Page<User> page = userRepository.findAll(pageable);
        System.out.println("总页数"+page.getTotalPages());
        System.out.println("总条数"+page.getTotalElements());
        System.out.println("分页数据"+page.getContent());
        //排序和分页
        // Pageable pageable1 = PageRequest.of(0,2, Sort.Direction.DESC,"username","id");
        //多个字段排序
        Sort.Order usernameOrder = Sort.Order.desc("username");
        Sort.Order idOrder = Sort.Order.desc("id");
        Sort sort1 = Sort.by(usernameOrder, idOrder);
        Pageable pageable1 = PageRequest.of(0,2,sort1);
        Page<User> all = userRepository.findAll(pageable1);
        System.out.println(all);
    }
    @Test
    public void  jpaRepoistroy(){
        User userEntity = new User();
        userEntity.setUsername("张三");
        userEntity.setPassword("12");
        //Example是一个接口，它用静态方法来Example，按照Example指定条件来查询。
        Example<User> example = Example.of(userEntity);
        List<User> all = userRepository.findAll(example);
        //在example查询的基础，还可以进行分页和排序。
        // List<User> all = userRepository.findAll(example，pageable);
        System.out.println(all);
    }
    //自定义查询
    @Test
    public void selfquery(){
        //按username查询
        List<User> zhangsan = userRepository.findByUsername("张三");
        System.out.println(zhangsan);
        //模糊查询
        List<User> zhangsanLike = userRepository.findByUsernameLike("%".concat("张").concat("%"));
        System.out.println(zhangsanLike);
        //模糊查询+排序
        List<User> zhangLikeSort = userRepository.findByUsernameLikeOrderByIdDesc("%".concat("三").concat("%"));
        System.out.println(zhangLikeSort);

        Pageable pageable = PageRequest.of(0,3);
        Page<User> pageInfo = userRepository.findByUsername("张三",pageable);
        System.out.println(pageInfo);
    }

    @Test
    public void query(){
        List<User> q1=userRepository.queryByUsernameAndPassword("张三","12");
        System.out.println(q1);
        List<User> q2=userRepository.selectByUsernameAndPassword("张三","12");
        System.out.println(q2);

        List<User> q3=userRepository.getListByUsernameAndPassword("张三","12");
        System.out.println(q3);

        Pageable pageable = PageRequest.of(0,1);
        List<User> q4 = userRepository.queryByUsernameAndPasswordByParam("张三", "12",pageable);
        System.out.println(q4);
    }

    @Test
    public void update2(){
        int i=  userRepository.updateUsernameById(7,"王五");
        System.out.println(i);
    }

}


















