package com.ido85.monitor.file.dto.promeconfig;

import lombok.Data;

import java.util.List;

/**
 * Created by dell on 2019/4/19.
 * @author dell
 * prometheus v2.8.0
 */
@Data
public class PromeAlertingDto {
    private List<PromeAlertManagerDto> alertmanagers;
}
