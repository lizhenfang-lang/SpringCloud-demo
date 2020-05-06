package com.lizhenfang.day01;

import com.lizhenfang.day01.fegin.OptionLogFeginService;
import com.lizhenfang.day01.model.OptionLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OptionLogTests {
    @Autowired
    private OptionLogFeginService optionLogFeginService;

    @Test
    public void save(){
        OptionLog optionLog = new OptionLog();
        optionLog.setId(UUID.randomUUID().toString());
        optionLog.setExeLog("throw info");
        optionLog.setMethodName("addLog");
        optionLog.setParameter("{id:1,methodName:\"addLog\"}");
        optionLog.setReturnValve("{result:true}");
        optionLogFeginService.addOptionLog(optionLog);
    }
}
