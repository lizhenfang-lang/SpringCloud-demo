package com.lizhenfang.day01.Dao;

import com.lizhenfang.day01.entity.Orde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface OrderRepository extends JpaRepository<Orde,Integer>,
        JpaSpecificationExecutor<Orde> {


}