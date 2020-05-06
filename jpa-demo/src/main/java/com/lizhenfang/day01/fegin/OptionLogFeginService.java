package com.lizhenfang.day01.fegin;

import com.lizhenfang.day01.model.OptionLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "mongo-demo")
public interface OptionLogFeginService {

    /**
     * 添加日志接口
     * @param optionLog
     * @return
     */
    @RequestMapping("/mongo/addOptionLog")
    public boolean addOptionLog(@RequestBody OptionLog optionLog);
}
