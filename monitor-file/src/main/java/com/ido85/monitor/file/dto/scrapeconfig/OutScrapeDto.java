package com.ido85.monitor.file.dto.scrapeconfig;

import com.ido85.monitor.file.dto.promeconfig.PromeScrapeConfigDto;
import lombok.Data;

import java.util.List;

/**
 * Created by dell on 2019/4/24.
 * @author dell
 */
@Data
public class OutScrapeDto {

    private String id;

    private List<PromeScrapeConfigDto> configData;

}
