package com.ido85.monitor.file.dto.alertconfig;

import com.ido85.monitor.file.dto.promebase.HttpConfigDto;
import lombok.Data;

/**
 * Created by dell on 2019/4/23.
 * @author dell
 */
@Data
public class AlertWebHookDto {

    private boolean send_resolved = true;

    /**
     * post
     */
    private String url;


    private HttpConfigDto http_config;
}
