package com.ido85.monitor.file.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;

/**
 * Created by dell on 2019/4/19.
 * @author dell
 */
@Configuration
public class SnakeYamlConfig {

    @Bean
    public Yaml yaml() throws Exception{
        return new Yaml();
    }
}
