package com.ido85.monitor.file.dto.promebase;

import lombok.Data;

/**
 * Created by dell on 2019/4/23.
 * @author dell
 */
@Data
public class TlsConfigDto {

    private String ca_file;

    private String cert_file;

    private String key_file;

    private String server_name;

    private boolean insecure_skip_verify;

}
