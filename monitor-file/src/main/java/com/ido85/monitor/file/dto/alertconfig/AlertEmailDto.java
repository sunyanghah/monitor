package com.ido85.monitor.file.dto.alertconfig;

import com.ido85.monitor.file.dto.promebase.TlsConfigDto;
import lombok.Data;

import java.util.Map;

/**
 * Created by dell on 2019/4/23.
 * @author dell
 */
@Data
public class AlertEmailDto {

    private boolean send_resolved = false;

    private String to;

    private String from;

    private String smarthost;

    private String hello;

    private String auth_username;

    private String auth_password;

    private String auth_secret;

    private String auth_identity;

    private String require_tls = "false";

    private TlsConfigDto tls_config;

    private String html;

    private String text;

    private Map<String,String> headers;

}
