package com.ido85.monitor.file.service;

import com.ido85.monitor.file.dto.ruleconfig.OutRuleDto;
import com.ido85.monitor.file.dto.ruleconfig.OutRuleSourceDto;
import com.ido85.monitor.file.dto.ruleconfig.RuleConfigDto;

import java.util.List;

/**
 * Created by dell on 2019/4/23.
 * @author dell
 */
public interface RuleConfigService {

    /**
     * 获取报警规则配置
     * @return
     * @throws Exception
     */
    List<OutRuleDto> getRuleConfigs() throws Exception;

    /**
     * 获取报警规则源配置
     * @return
     * @throws Exception
     */
    List<OutRuleSourceDto> getRuleConfigSources() throws Exception;

    /**
     * 新增告警规则
     * @param ruleConfigDto
     * @throws Exception
     */
    void addRuleConfig(RuleConfigDto ruleConfigDto) throws Exception;

    /**
     * 修改告警规则
     * @param ruleDto
     * @throws Exception
     */
    void updateRuleConfig(OutRuleDto ruleDto) throws Exception;

    /**
     * 删除告警规则
     * @param id
     * @throws Exception
     */
    void deleteRuleConfig(String id) throws Exception;

}
