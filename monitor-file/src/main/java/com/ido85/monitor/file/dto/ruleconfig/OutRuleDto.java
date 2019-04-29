package com.ido85.monitor.file.dto.ruleconfig;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by dell on 2019/4/24.
 * @author dell
 */
@Data
public class OutRuleDto {

    @NotBlank
    private String id;
    @NotNull
    private RuleConfigDto configData;

}
