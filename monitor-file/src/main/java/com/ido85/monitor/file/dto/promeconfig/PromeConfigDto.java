package com.ido85.monitor.file.dto.promeconfig;

import lombok.Data;

import java.util.List;

/**
 * Created by dell on 2019/4/19.
 * @author dell
 */
@Data
public class PromeConfigDto {

    /**
     * 全局配置
     */
    private PromeGlobalDto global;

    /**
     * 报警方式配置
     */
    private PromeAlertingDto alerting;

    /**
     * 报警规则文件
     */
    private List<String> rule_files;

    /**
     * 指标获取配置
     */
    private List<PromeScrapeConfigDto> scrape_configs;

}
