package com.ido85.monitor.file.dto.alertconfig;

import com.ido85.monitor.file.dto.promebase.HttpConfigDto;
import lombok.Data;

/**
 * Created by dell on 2019/4/23.
 * @author dell
 */
@Data
public class AlertGlobalDto {

    private String resolve_timeout = "5m";

    private String smtp_from;
    private String smtp_smarthost;
    private String smtp_hello;
    private String smtp_auth_username;
    private String smtp_auth_password;
    private String smtp_auth_identity;
    private String smtp_auth_secret;
    private String smtp_require_tls;
    private String slack_api_url;
    private String victorops_api_key;
    private String victorops_api_url;
    private String pagerduty_url;
    private String opsgenie_api_key;
    private String opsgenie_api_url;
    private String hipchat_api_url;
    private String hipchat_auth_token;
    private String wechat_api_url;
    private String wechat_api_secret;
    private String wechat_api_corp_id;

    private HttpConfigDto http_config;

}
