package com.ido85.monitor.file.dto.ruleconfig;

import lombok.Data;

import java.util.List;

/**
 * Created by dell on 2019/4/23.
 * @author dell
 */
@Data
public class RuleGroupDto {

    private String name;

    private List<RuleRulesDto> rules;

}
