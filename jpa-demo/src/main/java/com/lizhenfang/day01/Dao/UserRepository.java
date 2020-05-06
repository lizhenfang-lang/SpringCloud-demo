package com.lizhenfang.day01.Dao;

import com.lizhenfang.day01.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
    //findBy开始的方法
    List<User> findByUsername(String username);

    //模糊查询
    List<User> findByUsernameLike(String username);

    //排序
    List<User> findByUsernameLikeOrderByIdDesc(String username);

    //分页的时候指定排序条件
    Page<User> findByUsername(String username, Pageable pageable);
    //自定义删除方法
    @Transactional
    int deleteByUsername(String username);
    @Transactional
    int deleteByUsernameAndSex(String username,Integer sex);
    @Transactional
    int deleteBySexIsNull();

    //注解方法
    //?1,?2是方法的参数顺序
    @Query("select u from User u where  u.username=?1 and u.password=?2")
    List<User> queryByUsernameAndPassword(String username, String password);
    //原生SQL
    @Query(value = "select u.* from t_user u where u.username=?1 and u.password=?2", nativeQuery = true)
    List<User> selectByUsernameAndPassword(String username, String password);
    //Param参数注解
    @Query("select u from User u where username=:unmae and password=:pwd")
    List<User> getListByUsernameAndPassword(@Param("unmae") String username, @Param("pwd") String password);
    //Param参数注解,分页排序
    @Query("select u from User u where u.username=:uname and u.password=:pwd")
    List<User> queryByUsernameAndPasswordByParam(@Param("uname") String username,@Param("pwd") String password,
                                                 Pageable pageable);
    //通过Query注解，更新表字段
    @Modifying
    @Transactional
    @Query("update User u set u.username=?2 where id=?1")
    int updateUsernameById(Integer id, String username);

    Page<User> findAll(Specification<User> specification, Pageable pageable);
}