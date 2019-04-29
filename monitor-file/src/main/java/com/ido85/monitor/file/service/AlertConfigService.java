package com.ido85.monitor.file.service;

import com.ido85.monitor.file.dto.alertconfig.AlertConfigDto;
import com.ido85.monitor.file.dto.alertconfig.AlertGlobalDto;
import com.ido85.monitor.file.dto.alertconfig.AlertInhibitDto;
import com.ido85.monitor.file.dto.alertconfig.AlertRouteDto;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2019/4/22.
 * @author dell
 */
public interface AlertConfigService {

    /**
     * 获取alertmanager源配置
     * @return
     * @throws Exception
     */
    String getAlertConfigSource() throws Exception;

    /**
     * 获取alertmanager配置
     * @return
     * @throws Exception
     */
    AlertConfigDto getAlertConfig() throws Exception;

    /**
     * 修改报警方式配置
     * @param alertConfigDto
     * @throws Exception
     */
    void updateAlertConfig(AlertConfigDto alertConfigDto) throws Exception;

    /**
     * 修改报警方式配置
     * @param configContent
     * @throws Exception
     */
    void updateAlertConfigSource(String configContent) throws Exception;

    /**
     * 修改全局配置
     * @param alertGlobal
     * @throws Exception
     */
    void updateAlertGlobal(AlertGlobalDto alertGlobal) throws Exception;

    /**
     * 修改路由配置
     * @param alertRouteDto
     * @throws Exception
     */
    void updateAlertRoute(AlertRouteDto alertRouteDto) throws Exception;

    /**
     * 新增报警接收者
     * @param alertReceiver
     * @throws Exception
     */
    void addAlertReceiver(Map<String, Object> alertReceiver) throws Exception;

    /**
     * 修改报警接收者
     * @param alertReceiver
     * @throws Exception
     */
    void updateAlertReceiver(Map<String, Object> alertReceiver) throws Exception;

    /**
     * 删除报警接收者
     * @param name
     * @throws Exception
     */
    void deleteAlertReceiver(String name) throws Exception;

    /**
     * 修改抑制规则
     * @param alertInhibitDtoList
     * @throws Exception
     */
    void updateAlertInhibit(List<AlertInhibitDto> alertInhibitDtoList) throws Exception;
}
