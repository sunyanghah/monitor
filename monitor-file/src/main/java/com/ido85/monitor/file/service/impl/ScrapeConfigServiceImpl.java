package com.ido85.monitor.file.service.impl;

import com.ido85.monitor.common.exception.BusinessException;
import com.ido85.monitor.file.config.PromeConfig;
import com.ido85.monitor.file.dto.promeconfig.PromeConfigDto;
import com.ido85.monitor.file.dto.promeconfig.PromeScrapeConfigDto;
import com.ido85.monitor.file.service.PromeConfigService;
import com.ido85.monitor.file.service.ScrapeConfigService;
import com.ido85.monitor.file.util.ConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2019/4/22.
 * @author dell
 */
@Service
public class ScrapeConfigServiceImpl implements ScrapeConfigService {

    @Autowired
    private PromeConfig promeConfig;
    @Autowired
    private Yaml yaml;
    @Autowired
    private PromeConfigService promeConfigService;


    @Override
    public List<PromeScrapeConfigDto> getScrapeConfig() throws Exception {
        try {
            String configStr = ConfigUtil.getConfigSource(promeConfig.getPromeFilePath());
            PromeConfigDto promeConfigDto = yaml.loadAs(configStr,PromeConfigDto.class);
            return promeConfigDto.getScrape_configs();
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException("获取指标配置失败");
        }
    }

    @Override
    public void addScrapeConfig(PromeScrapeConfigDto promeScrapeConfigDto) throws Exception {
        String configStr = ConfigUtil.getConfigSource(promeConfig.getPromeFilePath());
        PromeConfigDto promeConfigDto = yaml.loadAs(configStr,PromeConfigDto.class);
        List<PromeScrapeConfigDto> scrapeConfigDtos = promeConfigDto.getScrape_configs();
        if (scrapeConfigDtos == null){
            scrapeConfigDtos = new ArrayList<>();
        }else{
            for (PromeScrapeConfigDto scrapeConfigDto : scrapeConfigDtos){
                if (scrapeConfigDto.getJob_name().equals(promeScrapeConfigDto.getJob_name())){
                    throw new BusinessException("任务名称不能重复");
                }
            }
        }
        scrapeConfigDtos.add(promeScrapeConfigDto);
        promeConfigDto.setScrape_configs(scrapeConfigDtos);
        ConfigUtil.putConfigSource(promeConfig.getPromeFilePath(),
                yaml.dumpAsMap(ConfigUtil.handleNoNull(promeConfigDto)));
        promeConfigService.reloadPromeConfig();
    }

    @Override
    public void updateScrapeConfig(PromeScrapeConfigDto promeScrapeConfigDto) throws Exception {
        String configStr = ConfigUtil.getConfigSource(promeConfig.getPromeFilePath());
        PromeConfigDto promeConfigDto = yaml.loadAs(configStr,PromeConfigDto.class);
        List<PromeScrapeConfigDto> scrapeConfigDtos = promeConfigDto.getScrape_configs();
        if (scrapeConfigDtos != null && scrapeConfigDtos.size() > 0){
            int index = scrapeConfigDtos.indexOf(promeScrapeConfigDto);
            if (index != -1){
                scrapeConfigDtos.remove(index);
                scrapeConfigDtos.add(promeScrapeConfigDto);
            }
            promeConfigDto.setScrape_configs(scrapeConfigDtos);
            ConfigUtil.putConfigSource(promeConfig.getPromeFilePath(),
                    yaml.dumpAsMap(ConfigUtil.handleNoNull(promeConfigDto)));
            promeConfigService.reloadPromeConfig();
        }
    }

    @Override
    public void deleteScrapeConfig(String jobName) throws Exception {
        String configStr = ConfigUtil.getConfigSource(promeConfig.getPromeFilePath());
        PromeConfigDto promeConfigDto = yaml.loadAs(configStr,PromeConfigDto.class);
        List<PromeScrapeConfigDto> scrapeConfigDtos = promeConfigDto.getScrape_configs();
        PromeScrapeConfigDto scrapeConfigDto = new PromeScrapeConfigDto();
        scrapeConfigDto.setJob_name(jobName);
        scrapeConfigDtos.remove(scrapeConfigDto);
        promeConfigDto.setScrape_configs(scrapeConfigDtos);
        ConfigUtil.putConfigSource(promeConfig.getPromeFilePath(),
                yaml.dumpAsMap(ConfigUtil.handleNoNull(promeConfigDto)));
        promeConfigService.reloadPromeConfig();
    }

}
