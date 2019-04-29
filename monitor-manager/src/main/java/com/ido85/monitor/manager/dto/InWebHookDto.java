package com.ido85.monitor.manager.dto;

import com.ido85.monitor.manager.config.constants.AlertStatus;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2019/4/28.
 * @author dell
 */
@Data
public class InWebHookDto {

    private String version;

    private String groupKey;

    private AlertStatus status;

    private String receiver;

    private Map<String,Object> groupLabels;

    private Map<String,Object> commonLabels;

    private Map<String,Object> commonAnnotations;

    private String externalURL;

    private List<InWebHookAlertDto> alerts;

}
