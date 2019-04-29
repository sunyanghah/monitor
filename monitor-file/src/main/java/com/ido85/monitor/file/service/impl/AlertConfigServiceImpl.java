package com.ido85.monitor.file.service.impl;

import com.alibaba.fastjson.JSON;
import com.ido85.monitor.common.dto.HttpResult;
import com.ido85.monitor.common.exception.BusinessException;
import com.ido85.monitor.common.util.HttpUtil;
import com.ido85.monitor.file.config.AlertConfig;
import com.ido85.monitor.file.dto.alertconfig.AlertConfigDto;
import com.ido85.monitor.file.dto.alertconfig.AlertGlobalDto;
import com.ido85.monitor.file.dto.alertconfig.AlertInhibitDto;
import com.ido85.monitor.file.dto.alertconfig.AlertRouteDto;
import com.ido85.monitor.file.service.AlertConfigService;
import com.ido85.monitor.file.util.ConfigUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2019/4/22.
 * @author dell
 */
@Service
public class AlertConfigServiceImpl implements AlertConfigService {

    @Autowired
    private AlertConfig alertConfig;
    @Autowired
    private Yaml yaml;

    @Override
    public String getAlertConfigSource() throws Exception {
        try {
            return ConfigUtil.getConfigSource(alertConfig.getAlertFilePath());
        }catch (Exception e){
            throw new BusinessException("获取告警配置失败");
        }
    }

    @Override
    public AlertConfigDto getAlertConfig() throws Exception {
        AlertConfigDto alertConfigDto = yaml.loadAs(ConfigUtil.getConfigSource(
                alertConfig.getAlertFilePath()),AlertConfigDto.class);
        if (alertConfigDto == null){
            throw new BusinessException("未获取到告警配置");
        }
        return alertConfigDto;
    }

    @Override
    public void updateAlertConfig(AlertConfigDto alertConfigDto) throws Exception {
        try {
            String configString = yaml.dumpAsMap(ConfigUtil.handleNoNull(alertConfigDto));
            ConfigUtil.putConfigSource(alertConfig.getAlertFilePath(), configString);
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException("修改配置文件失败，请检查配置是否正确");
        }

        reloadAlertConfig();

    }

    @Override
    public void updateAlertConfigSource(String configContent) throws Exception {
        try {
            ConfigUtil.putConfigSource(alertConfig.getAlertFilePath(), yaml.dumpAsMap(yaml.load(JSON.toJSONString(yaml.load(configContent)))));
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException("修改配置文件失败，请检查配置是否正确");
        }
        reloadAlertConfig();
    }

    @Override
    public void updateAlertGlobal(AlertGlobalDto alertGlobal) throws Exception {
        AlertConfigDto alertConfigDto = this.getAlertConfig();
        alertConfigDto.setGlobal(alertGlobal);
        this.updateAlertConfig(alertConfigDto);
    }

    @Override
    public void updateAlertRoute(AlertRouteDto alertRouteDto) throws Exception {
        AlertConfigDto alertConfigDto = this.getAlertConfig();
        alertConfigDto.setRoute(alertRouteDto);
        this.updateAlertConfig(alertConfigDto);
    }

    @Override
    public void addAlertReceiver(Map<String, Object> alertReceiver) throws Exception {
        Object name = alertReceiver.get("name");
        if (name == null || StringUtils.isBlank(name.toString())){
            throw new BusinessException("名称不能为空");
        }
        AlertConfigDto alertConfigDto = this.getAlertConfig();
        List<Map<String,Object>> receivers = alertConfigDto.getReceivers();
        if (receivers != null && receivers.size() > 0){
            String nameStr = name.toString();
            for (Map<String,Object> receiver : receivers){
                if (nameStr.equals(receiver.get("name"))){
                    throw new BusinessException("名称不能重复");
                }
            }

            receivers.add(alertReceiver);
            this.updateAlertConfig(alertConfigDto);

        }

    }

    @Override
    public void updateAlertReceiver(Map<String, Object> alertReceiver) throws Exception {
        Object name = alertReceiver.get("name");
        if (name == null || StringUtils.isBlank(name.toString())){
            throw new BusinessException("名称不能为空");
        }
        String nameStr = name.toString();
        AlertConfigDto alertConfigDto = this.getAlertConfig();
        List<Map<String,Object>> receivers = alertConfigDto.getReceivers();
        if (receivers != null && receivers.size() > 0){
            for (int i=0;i<receivers.size();i++){
                if (nameStr.equals(receivers.get(i).get("name"))){
                    receivers.set(i,alertReceiver);
                }
            }
            this.updateAlertConfig(alertConfigDto);
        }
    }

    @Override
    public void deleteAlertReceiver(String name) throws Exception {
        AlertConfigDto alertConfigDto = this.getAlertConfig();
        List<Map<String,Object>> receivers = alertConfigDto.getReceivers();
        if (receivers != null && receivers.size() > 0){
            int index = -1;
            for (int i=0;i<receivers.size();i++){
                if (name.equals(receivers.get(i).get("name"))){
                    index = i;
                }
            }
            if (index >= 0){
                receivers.remove(index);
            }
            this.updateAlertConfig(alertConfigDto);
        }
    }

    @Override
    public void updateAlertInhibit(List<AlertInhibitDto> alertInhibitDtoList) throws Exception {
        AlertConfigDto alertConfigDto = this.getAlertConfig();
        alertConfigDto.setInhibit_rules(alertInhibitDtoList);
        this.updateAlertConfig(alertConfigDto);
    }


    private void reloadAlertConfig() throws Exception{
        try {
            HttpResult httpResult = HttpUtil.doPost(alertConfig.getAlertApiUrl() + alertConfig.getAlertReloadUrl(), null);
            if (!httpResult.isSuccess()){
                throw new BusinessException("生效配置失败，配置语法错误");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException("配置已修改但生效失败，请检查配置是否正确，重试或手动生效");
        }
    }
}
