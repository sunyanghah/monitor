package com.ido85.monitor.file.dto.promeconfig;

import lombok.Data;

import java.util.Map;

/**
 * Created by dell on 2019/4/19.
 * @author dell
 * prometheus v2.8.0 版本全局配置
 */
@Data
public class PromeGlobalDto {

    private String scrape_interval = "1m";

    private String scrape_timeout = "10s";

    private String evaluation_interval = "1m";

    private Map<String,String> external_labels;
}
