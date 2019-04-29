package com.ido85.monitor.file.dto.alertconfig;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2019/4/23.
 * @author dell
 */
@Data
public class AlertConfigDto {

    private AlertGlobalDto global;

    private List<String> templates;

    private AlertRouteDto route;

    private List<Map<String,Object>> receivers;

    private List<AlertInhibitDto> inhibit_rules;
}
