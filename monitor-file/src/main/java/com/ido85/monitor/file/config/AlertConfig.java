package com.ido85.monitor.file.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by dell on 2019/4/22.
 * @author dell
 */
@Data
@Component
public class AlertConfig {

    @Value("${alertFilePath}")
    private String alertFilePath;

    @Value("${alertApiUrl:127.0.0.1:9093}")
    private String alertApiUrl;

    @Value("${alertReloadUrl:/-/reload}")
    private String alertReloadUrl;

}
