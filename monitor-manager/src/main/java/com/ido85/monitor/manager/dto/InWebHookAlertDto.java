package com.ido85.monitor.manager.dto;

import com.ido85.monitor.manager.config.constants.AlertStatus;
import lombok.Data;

import java.util.Map;

/**
 * Created by dell on 2019/4/28.
 * @author dell
 */
@Data
public class InWebHookAlertDto {

    private AlertStatus status;

    private Map<String,Object> labels;

    private Map<String,Object> annotations;

    private String startsAt;

    private String endsAt;

    private String generatorURL;

}
