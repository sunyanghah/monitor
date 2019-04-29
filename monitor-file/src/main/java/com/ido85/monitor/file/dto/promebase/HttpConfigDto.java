package com.ido85.monitor.file.dto.promebase;

import lombok.Data;

/**
 * Created by dell on 2019/4/23.
 * @author dell
 */
@Data
public class HttpConfigDto {

    private BasicAuthDto basic_auth;

    private String bearer_token;

    private String bearer_token_file;

    private TlsConfigDto tls_config;

    private String proxy_url;

}
