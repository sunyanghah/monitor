package com.ido85.monitor.file.service.impl;

import com.ido85.monitor.common.exception.BusinessException;
import com.ido85.monitor.file.config.PromeConfig;
import com.ido85.monitor.file.dto.ruleconfig.OutRuleDto;
import com.ido85.monitor.file.dto.ruleconfig.OutRuleSourceDto;
import com.ido85.monitor.file.dto.ruleconfig.RuleConfigDto;
import com.ido85.monitor.file.dto.ruleconfig.RuleGroupDto;
import com.ido85.monitor.file.service.PromeConfigService;
import com.ido85.monitor.file.service.RuleConfigService;
import com.ido85.monitor.file.util.ConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by dell on 2019/4/23.
 * @author dell
 */
@Service
public class RuleConfigServiceImpl implements RuleConfigService {

    @Autowired
    private PromeConfig promeConfig;
    @Autowired
    private Yaml yaml;
    @Autowired
    private PromeConfigService promeConfigService;

    @Override
    public List<OutRuleDto> getRuleConfigs() throws Exception {
        try {
            List<OutRuleDto> ruleConfigDtoList = new ArrayList<>();
            String rulePath = promeConfig.getRuleFilePath();
            File file = new File(rulePath);
            for (File patternFile : file.getParentFile().listFiles()) {
                if (Pattern.matches(ConfigUtil.handlePattern(file.getName()), patternFile.getName())) {
                    OutRuleDto outRuleDto = new OutRuleDto();
                    outRuleDto.setId(patternFile.getName());
                    outRuleDto.setConfigData(readRuleConfig(ConfigUtil.getConfigSource(patternFile)));
                    ruleConfigDtoList.add(outRuleDto);
                }
            }
            return ruleConfigDtoList;
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException("获取报警规则失败");
        }
    }

    @Override
    public List<OutRuleSourceDto> getRuleConfigSources() throws Exception {
        try {
            List<OutRuleSourceDto> ruleConfigSources = new ArrayList<>();
            String rulePath = promeConfig.getRuleFilePath();
            File file = new File(rulePath);
            for (File patternFile : file.getParentFile().listFiles()) {
                if (Pattern.matches(ConfigUtil.handlePattern(file.getName()), patternFile.getName())) {
                    OutRuleSourceDto outRuleSourceDto = new OutRuleSourceDto();
                    outRuleSourceDto.setId(patternFile.getName());
                    outRuleSourceDto.setConfigData(ConfigUtil.getConfigSource(patternFile));
                    ruleConfigSources.add(outRuleSourceDto);
                }
            }
            return ruleConfigSources;
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException("获取报警规则失败");
        }
    }

    @Override
    public void addRuleConfig(RuleConfigDto ruleConfigDto) throws Exception {
        checkRuleName(ruleConfigDto.getGroups());
        File file = new File(promeConfig.getRuleFilePath());
        ConfigUtil.putConfigSource(file.getParent()+"/"+System.currentTimeMillis()+".yml",
                yaml.dumpAsMap(ConfigUtil.handleNoNull(ruleConfigDto)));
        promeConfigService.reloadPromeConfig();
    }

    @Override
    public void updateRuleConfig(OutRuleDto ruleDto) throws Exception {
        checkRuleName(ruleDto.getConfigData().getGroups());
        File file = new File(promeConfig.getRuleFilePath());
        ConfigUtil.putConfigSource(file.getParent()+"/"+ruleDto.getId(),
                yaml.dumpAsMap(ConfigUtil.handleNoNull(ruleDto.getConfigData())));
        promeConfigService.reloadPromeConfig();
    }

    @Override
    public void deleteRuleConfig(String id) throws Exception {
        File file = new File(promeConfig.getRuleFilePath());
        file = new File(file.getParent()+"/"+id);
        if (file.exists()){
            file.delete();
        }
        promeConfigService.reloadPromeConfig();
    }

    private void checkRuleName(List<RuleGroupDto> groups) throws Exception{
        Set<String> set = new HashSet<>();
        if (groups != null && groups.size() > 0){
            for (RuleGroupDto ruleGroupDto : groups){
                if(set.contains(ruleGroupDto.getName())){
                    throw new BusinessException("不允许一次设置两个相同名称的分组");
                }else{
                    set.add(ruleGroupDto.getName());
                }
            }
        }
    }


    private RuleConfigDto readRuleConfig(String fileContent) throws Exception{
        return yaml.loadAs(fileContent,RuleConfigDto.class);
    }

}
