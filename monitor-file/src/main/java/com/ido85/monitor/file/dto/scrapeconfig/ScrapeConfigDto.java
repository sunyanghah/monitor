package com.ido85.monitor.file.dto.scrapeconfig;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2019/4/22.
 * @author dell
 */
@Data
public class ScrapeConfigDto {

    private List<String> targets;

    private Map<String,String> labels;

}
