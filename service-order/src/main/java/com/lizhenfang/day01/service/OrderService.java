package com.lizhenfang.day01.service;

import com.lizhenfang.day01.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    /**
     * 根据Id，查询order
     * @param id
     * @return
     */
    public Order getOrderById(Integer id){
        Order order = new Order();
        order.setId(id);
        order.setOrderno(System.currentTimeMillis()+"");
        order.setUserId(1);
        return  order;
    }
}
