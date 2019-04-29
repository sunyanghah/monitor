package com.ido85.monitor.file.dto.promeconfig;

import lombok.Data;

import java.util.List;

/**
 * Created by dell on 2019/4/19.
 * @author dell
 */
@Data
public class PromeFileSdConfigDto {

    /**
     * 文件路径，支持通配符
     */
    private List<String> files;

    /**
     * 刷新间隔
     */
    private String refresh_interval = "5m";
}
