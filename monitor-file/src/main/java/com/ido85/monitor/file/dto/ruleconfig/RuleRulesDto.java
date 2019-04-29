package com.ido85.monitor.file.dto.ruleconfig;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Created by dell on 2019/4/23.
 * @author dell
 */
public class RuleRulesDto {
    @Getter
    @Setter
    private String alert;
    @Getter
    @Setter
    private String expr;

    private String forDuration = "0s";
    @Getter
    @Setter
    private Map<String,String> labels;
    @Getter
    @Setter
    private Map<String,String> annotations;

    public String getFor(){
        return this.forDuration;
    }
    public void setFor(String forDuration){
        this.forDuration = forDuration;
    }
}
