package com.ido85.monitor.file.service;

import com.ido85.monitor.file.dto.promeconfig.PromeConfigDto;

/**
 * Created by dell on 2019/4/17.
 * @author dell
 */
public interface PromeConfigService {

    /**
     * 获取prometheus配置文件
     * @throws Exception
     * @return
     */
    PromeConfigDto getPromeConfig() throws Exception;

    /**
     * 获取Prometheus原配置文件
     * @throws Exception
     * @return
     */
    String getPromeConfigSource() throws Exception;

    /**
     * 修改prometheus配置
     * @param promeConfigDto
     * @throws Exception
     */
    void updatePromeConfig(PromeConfigDto promeConfigDto) throws Exception;

    /**
     * 修改Prometheus配置
     * @param configContent
     * @throws Exception
     */
    void updatePromeConfigSource(String configContent) throws Exception;

    /**
     * 重新加载配置
     * @throws Exception
     */
    void reloadPromeConfig() throws Exception;
}
