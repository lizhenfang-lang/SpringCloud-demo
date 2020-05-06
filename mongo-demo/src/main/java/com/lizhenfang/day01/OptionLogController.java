package com.lizhenfang.day01;

import com.lizhenfang.day01.dao.OptionLogDao;
import com.lizhenfang.day01.model.OptionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mongo/")
public class OptionLogController {
    @Autowired
    private OptionLogDao optionLogDao;

    //添加日志接口
    @RequestMapping("addOptionLog")
    public boolean addOptionLog(@RequestBody OptionLog optionLog){
        OptionLog save = optionLogDao.save(optionLog);
        return true;
    }
}
