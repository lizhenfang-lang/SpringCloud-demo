package com.lizhenfang.day01.service;

import com.lizhenfang.day01.mapper.OrderMapper;
import com.lizhenfang.day01.model.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderService {
    @Resource
    OrderMapper orderMapper;
    /**
     * 根据Id，查询order
     * @param id
     * @return
     */
    public Order getOrderById(Integer id){
        return  orderMapper.getOrderById(id);

    }
}
