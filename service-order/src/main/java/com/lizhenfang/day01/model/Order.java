package com.lizhenfang.day01.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
public class Order {
    private Integer id;
    private String orderno;
    private Integer userId;
    private String username;


}
