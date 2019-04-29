package com.ido85.monitor.file.web;

import com.ido85.monitor.common.platform.RP;
import com.ido85.monitor.file.dto.ruleconfig.OutRuleDto;
import com.ido85.monitor.file.dto.ruleconfig.OutRuleSourceDto;
import com.ido85.monitor.file.dto.ruleconfig.RuleConfigDto;
import com.ido85.monitor.file.service.RuleConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by dell on 2019/4/23.
 * @author dell
 */
@RestController
@RequestMapping("/rule")
public class RuleConfigController {

    @Autowired
    private RuleConfigService ruleConfigService;

    @GetMapping
    public RP getRuleConfig() throws Exception{
        List<OutRuleDto> ruleConfigDtoList = ruleConfigService.getRuleConfigs();
        return RP.buildSuccess(ruleConfigDtoList);
    }


    @GetMapping("/source")
    public RP getRuleConfigSource() throws Exception{
        List<OutRuleSourceDto> ruleConfigSourceList = ruleConfigService.getRuleConfigSources();
        return RP.buildSuccess(ruleConfigSourceList);
    }

    @PostMapping
    public RP addRuleConfig(@RequestBody RuleConfigDto ruleConfigDto) throws Exception{
        ruleConfigService.addRuleConfig(ruleConfigDto);
        return RP.buildSuccess("新增告警规则成功");
    }

    @PutMapping
    public RP updateRuleConfig(@RequestBody OutRuleDto ruleDto) throws Exception{
        ruleConfigService.updateRuleConfig(ruleDto);
        return RP.buildSuccess("修改告警规则成功");
    }

    @DeleteMapping("/{id}")
    public RP deleteRuleConfig(@PathVariable("id")String id) throws Exception{
        ruleConfigService.deleteRuleConfig(id);
        return RP.buildSuccess("删除告警规则成功");
    }


}
