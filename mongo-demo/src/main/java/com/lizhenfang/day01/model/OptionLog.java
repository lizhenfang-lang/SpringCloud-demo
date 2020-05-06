package com.lizhenfang.day01.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class OptionLog {

    @Id
    private String id;

    private String methodName;

    private String parameter;

    private String returnValve;

    private String exeLog;

}
