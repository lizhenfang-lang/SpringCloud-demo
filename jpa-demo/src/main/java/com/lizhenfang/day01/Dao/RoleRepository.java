package com.lizhenfang.day01.Dao;

import com.lizhenfang.day01.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Modifying
    @Transactional
    @Query("update Role r set r.name=:name where r.id=:id")
    int updateNameById(@Param("id") Integer id, @Param("name") String name);
}