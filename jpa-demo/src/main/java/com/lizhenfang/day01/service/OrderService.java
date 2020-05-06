package com.lizhenfang.day01.service;

import com.lizhenfang.day01.Dao.OrderRepository;
import com.lizhenfang.day01.entity.Orde;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Page<Orde> getPageInfo(Orde orderEntity, Integer pageNum, Integer pageSize){
        Specification<Orde> specification = new Specification<Orde>() {
            @Override
            public Predicate toPredicate(Root<Orde> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(orderEntity.getUsername()!=null){
                    Predicate usernamePredicate = cb.like(root.get("user").get("username"), "%".concat(orderEntity.getUsername()).concat("%"));
                    predicates.add(usernamePredicate);
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        return orderRepository.findAll(specification,pageable);
    }
}