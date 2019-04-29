package com.ido85.monitor.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by dell on 2019/4/12.
 * @author dell
 */
@SpringBootApplication(scanBasePackages = {"com.ido85.monitor"})
@MapperScan(basePackages = {"com.ido85.monitor.auth.mapper"})
public class MonitorAuthApplication {
    public static void main(String[] args){
        SpringApplication.run(MonitorAuthApplication.class,args);
    }
}
