package com.ido85.monitor.file.service.impl;

import com.alibaba.fastjson.JSON;
import com.ido85.monitor.common.dto.HttpResult;
import com.ido85.monitor.common.exception.BusinessException;
import com.ido85.monitor.common.util.HttpUtil;
import com.ido85.monitor.file.config.PromeConfig;
import com.ido85.monitor.file.dto.promeconfig.PromeConfigDto;
import com.ido85.monitor.file.service.PromeConfigService;
import com.ido85.monitor.file.util.ConfigUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

/**
 * Created by dell on 2019/4/17.
 * @author dell
 */
@Service
public class PromeConfigServiceImpl implements PromeConfigService {

    @Autowired
    private PromeConfig promeConfig;
    @Autowired
    private Yaml yaml;

    @Override
    public PromeConfigDto getPromeConfig() throws Exception {
        try {
            String yamlSource = this.getPromeConfigSource();
            if (StringUtils.isNotBlank(yamlSource)) {
                PromeConfigDto promeConfigDto = yaml.loadAs(yamlSource, PromeConfigDto.class);
                return promeConfigDto;
            } else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException("文件解析失败，建议通过开放式配置并修改");
        }
    }


    @Override
    public String getPromeConfigSource() throws Exception {
        return ConfigUtil.getConfigSource(promeConfig.getPromeFilePath());
    }

    @Override
    public void updatePromeConfig(PromeConfigDto promeConfigDto) throws Exception {
        try {
            String configString = yaml.dumpAsMap(ConfigUtil.handleNoNull(promeConfigDto));
            ConfigUtil.putConfigSource(promeConfig.getPromeFilePath(), configString);
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException("修改配置文件失败，请检查配置是否正确");
        }
        reloadPromeConfig();
    }

    @Override
    public void updatePromeConfigSource(String configContent) throws Exception {
        try {
            ConfigUtil.putConfigSource(promeConfig.getPromeFilePath(), yaml.dumpAsMap(yaml.load(JSON.toJSONString(yaml.load(configContent)))));
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException("修改配置文件失败，请检查配置是否正确");
        }
        reloadPromeConfig();
    }

    public void reloadPromeConfig() throws Exception{
        try {
            HttpResult httpResult = HttpUtil.doPost(promeConfig.getPromeApiUrl() + promeConfig.getPromeReloadUrl(), null);
            if (!httpResult.isSuccess()){
                throw new BusinessException("生效配置失败，配置语法错误");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException("配置已修改但生效失败，请检查配置是否正确，重试或手动生效");
        }
    }

}
