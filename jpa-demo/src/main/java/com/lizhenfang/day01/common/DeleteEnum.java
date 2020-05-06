package com.lizhenfang.day01.common;

/**
 * 软删除枚举类
 */
public enum DeleteEnum {
    YES(1),NO(0);

    private  Integer value;

    DeleteEnum(Integer value) {
        this.value = value;
    }
}
