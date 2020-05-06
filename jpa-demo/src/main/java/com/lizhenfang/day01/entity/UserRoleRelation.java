package com.lizhenfang.day01.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

//Entity注解和数据表有映射关系
//如果数据库表和实体类不一致，需要table注解，指定表名
@Setter
@Getter
@ToString
@Entity
@Table(name = "t_user_role_relation")
public class UserRoleRelation {
    /** 主键的生成策略： IDENTITY、AUTO **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //用户Id
    private Integer userId;
    //角色Id
    private Integer roleId;
}
