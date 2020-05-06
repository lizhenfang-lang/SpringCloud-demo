package com.lizhenfang.day01.service;

import com.lizhenfang.day01.Dao.UserRepository;
import com.lizhenfang.day01.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> getPageInfo(User userEntity, Integer pageNum, Integer pageSize) {
        //分页
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        //构建Specification
        Specification<User> specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder cb) {
                //保存多个查询条件
                List<Predicate> predicates = new ArrayList<>();
                //查询字段不为空的时候，添加条件
                if (userEntity.getUsername() != null) {
                    //构建条件
                    Predicate predicate = cb.like(root.get("username"), "%".concat(userEntity.getUsername()).concat("%"));
                    //添加到predicates
                    predicates.add(predicate);
                }
                if(userEntity.getOrderNo()!=null){
                    //user链接查询order表，join是orderList，按订单编号模糊查询
                    Predicate predicate = cb.like(root.join("orderList").get("orderNo"),"%".concat(userEntity.getOrderNo().concat("%")));
                    predicates.add(predicate);
                }
                //返回的接口
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return  userRepository.findAll(specification,pageable);
    }
}
