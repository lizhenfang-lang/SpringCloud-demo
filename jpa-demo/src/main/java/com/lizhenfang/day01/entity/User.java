package com.lizhenfang.day01.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lizhenfang.day01.common.DeleteEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
@Entity
@Table(name = "t_user")
@ApiModel("用户实体类")
public class User{
    /** 主键的生成策略： IDENTITY、AUTO **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty("用户登录名称")
    private String username;

    @Column(columnDefinition = "varchar(64) DEFAULT NULL COMMENT '密码'")
    private String password;
    //字段精度设置：precision长度，scale小数的位数
    @Column(precision = 10,scale = 2,columnDefinition = "double(10,2) DEFAULT NULL COMMENT '积分'")
    private Double score;

    private Integer sex;

    private Integer classesId;
    //非数据的映射字段，加Transient注解
    @Transient
    private String classesName;

    @Transient
    private String orderNo;

    //枚举
    @Enumerated(EnumType.ORDINAL)
    private DeleteEnum isDelete;
    //大字段lob
    @Lob
    private String content;

    //按java命名规范，在表里生成create_time字段
    private Date createTime;
    //指定时间的精度,默认是datetime
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;


    /*//忽略Order的user属性
    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "userId")
    private List<Order> orderList;*/

    @JsonIgnoreProperties("user")
    @OneToMany
    @JoinColumn(name = "userId",insertable = false,updatable = false)
    private List<Orde> orderList;

    //多对多关系
    //JoinTable name是关系表的表名 joinColumns是主表在关系表对应的列，inverseJoinColumns从表对应的列
    @JsonIgnoreProperties("userList")
    @ManyToMany
    @JoinTable(name = "t_user_role_relation",joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<Role> roleList;
}