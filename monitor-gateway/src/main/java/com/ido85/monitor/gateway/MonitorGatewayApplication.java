package com.ido85.monitor.gateway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by dell on 2019/4/12.
 * @author dell
 */
@SpringBootApplication(scanBasePackages = {"com.ido85.monitor"})
@MapperScan(basePackages = {"com.ido85.monitor.gateway.mapper"})
@EnableZuulProxy
@EnableOAuth2Sso
@EnableSwagger2
public class MonitorGatewayApplication {
    public static void main(String[] args){
        SpringApplication.run(MonitorGatewayApplication.class,args);
    }
}
