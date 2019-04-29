package com.ido85.monitor.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by dell on 2019/4/17.
 * @author dell
 */
@SpringBootApplication(scanBasePackages = {"com.ido85.monitor"})
public class MonitorManagerApplication {
    public static void main(String[] args){
        SpringApplication.run(MonitorManagerApplication.class,args);
    }
}
