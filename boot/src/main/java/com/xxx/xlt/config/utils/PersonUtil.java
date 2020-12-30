package com.xxx.xlt.config.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PersonUtil {
    private static String name;
    private static String number;
    @Value("${person.name}")
    private String name_;
    @Value("${person.number}")
    private String number_;

    @PostConstruct
    public void setStaticParam() {
        PersonUtil.number=this.number_;
        PersonUtil.name=this.name_;
    }

    public static String getName() {
        return name;
    }

    public static String getNumber() {
        return number;
    }
}
