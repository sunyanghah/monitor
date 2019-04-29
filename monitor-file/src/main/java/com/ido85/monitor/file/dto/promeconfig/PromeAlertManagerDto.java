package com.ido85.monitor.file.dto.promeconfig;

import com.ido85.monitor.file.dto.promebase.BasicAuthDto;
import com.ido85.monitor.file.dto.promebase.TlsConfigDto;
import lombok.Data;

import java.util.List;

/**
 * Created by dell on 2019/4/19.
 * @author dell
 */
@Data
public class PromeAlertManagerDto {

    /**
     * 请求协议方案，默认http
     */
    private String scheme = "http";

    /**
     * 推送报警的超时时间，默认10s
     */
    private String timeout = "10s";

    private String path_prefix = "/";

    private BasicAuthDto basic_auth;

    private String bearer_token;

    private String bearer_token_file;

    private String proxy_url;

    private TlsConfigDto tls_config;

    /**
     *
     */
    private List<PromeFileSdConfigDto> file_sd_configs;
}
