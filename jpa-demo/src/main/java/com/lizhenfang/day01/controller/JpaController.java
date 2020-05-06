package com.lizhenfang.day01.controller;


import com.lizhenfang.day01.Dao.OrderRepository;
import com.lizhenfang.day01.Dao.RoleRepository;
import com.lizhenfang.day01.Dao.UserRepository;
import com.lizhenfang.day01.entity.Orde;
import com.lizhenfang.day01.entity.User;
import com.lizhenfang.day01.service.OrderService;
import com.lizhenfang.day01.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Api(tags = "JPA测试controller")
@RestController
@RequestMapping("/jpa")
public class JpaController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;
    @ApiOperation(value = "用户分页列表接口")
    @RequestMapping("/user")
    public Page<User> getPageInfo(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "3") Integer pageSize, User user){
        return  userService.getPageInfo(user,pageNum,pageSize);
    }

    @RequestMapping("order")
    public Page<Orde> order(Orde orderEntity,
                            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        return orderService.getPageInfo(orderEntity,pageNum,pageSize);
    }

    @RequestMapping("role")
    public Object getRolerAll(){
        return roleRepository.findAll();
    }
}
