package com.lizhenfang.day01;

import com.lizhenfang.day01.Dao.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuditingTests {
    @Autowired
    private RoleRepository roleRepository;

    @Test
//    @Transactional
    public void test(){
        /*//添加
        Role roleEntity = new Role();
        roleEntity.setName("test");
       Role role = roleRepository.save(roleEntity);*/
        //修改
        roleRepository.updateNameById(4,"运营");
    }
}
