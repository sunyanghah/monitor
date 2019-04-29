package com.ido85.monitor.file.service;

import com.ido85.monitor.file.dto.promeconfig.PromeScrapeConfigDto;
import com.ido85.monitor.file.dto.scrapeconfig.OutScrapeDto;
import com.ido85.monitor.file.dto.scrapeconfig.ScrapeConfigDto;

import java.util.List;

/**
 * Created by dell on 2019/4/22.
 * @author dell
 */
public interface ScrapeConfigService {

    /**
     * 获取
     * @return
     * @throws Exception
     */
    List<PromeScrapeConfigDto> getScrapeConfig() throws Exception;

    /**
     * 添加指标数据源配置
     * @param promeScrapeConfigDto
     * @throws Exception
     */
    void addScrapeConfig(PromeScrapeConfigDto promeScrapeConfigDto) throws Exception;

    /**
     * 修改指标数据源配置
     * @param promeScrapeConfigDto
     * @throws Exception
     */
    void updateScrapeConfig(PromeScrapeConfigDto promeScrapeConfigDto) throws Exception;

    /**
     * 删除指标数据源配置
     * @param jobName
     * @throws Exception
     */
    void deleteScrapeConfig(String jobName) throws Exception;
}
