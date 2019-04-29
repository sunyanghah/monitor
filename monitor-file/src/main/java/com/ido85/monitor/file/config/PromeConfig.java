package com.ido85.monitor.file.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by dell on 2019/4/18.
 * @author dell
 */
@Component
@Data
public class PromeConfig {

    @Value("${promeFilePath}")
    private String promeFilePath;

    @Value("${promeApiUrl:127.0.0.1:9090}")
    private String promeApiUrl;

    @Value("${promeReloadUrl:/-/reload}")
    private String promeReloadUrl;

    @Value("${ruleFilePath}")
    private String ruleFilePath;

}
