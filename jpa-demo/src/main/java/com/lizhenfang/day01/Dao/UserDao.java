package com.lizhenfang.day01.Dao;

import com.lizhenfang.day01.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * 持久层注解
 */
@Repository
public class UserDao {
    @Autowired
    private EntityManager entityManager;

    /**
     * 新增，修改
     * @param entity
     * @return
     */
    @Transactional
    public User save(User entity){
        User user = entityManager.merge(entity);
        return user;
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */

    public User findById(Integer id){
        User user=entityManager.find(User.class,id);
        return user;
    }

    /**
     * 按Id删除
     * 先查询
     * @param id
     */
    @Transactional
    public void deleteById(Integer id){
        User user = findById(id);
        entityManager.remove(user);
    }

    /**
     * 查询所有
     * @return
     */
    public List<User> findAll(){
        Query query=entityManager.createQuery("from User u");
        return  query.getResultList();
    }

    /**
     * 按用户名称模糊查询
     * @param username
     * @return
     */
    public List<User> findByUsername(String username){
        Query query = entityManager.createQuery("from User u where u.username like :username");
        //拼接%，实现模糊查询
        String likeStr = "%".concat(username).concat("%");
        query.setParameter("username",likeStr);
        return query.getResultList();
    }
}
