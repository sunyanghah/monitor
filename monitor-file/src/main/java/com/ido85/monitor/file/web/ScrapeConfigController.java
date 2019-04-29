package com.ido85.monitor.file.web;

import com.ido85.monitor.common.platform.RP;
import com.ido85.monitor.file.dto.promeconfig.PromeScrapeConfigDto;
import com.ido85.monitor.file.dto.scrapeconfig.OutScrapeDto;
import com.ido85.monitor.file.dto.scrapeconfig.ScrapeConfigDto;
import com.ido85.monitor.file.service.ScrapeConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by dell on 2019/4/22.
 * @author dell
 * 监控源管理
 */
@RestController
@RequestMapping("/scrape")
public class ScrapeConfigController {

    @Autowired
    private ScrapeConfigService scrapeConfigService;

    @GetMapping
    public RP getScrapeConfig() throws Exception{
        List<PromeScrapeConfigDto> scrapeConfigDtoList = scrapeConfigService.getScrapeConfig();
        return RP.buildSuccess(scrapeConfigDtoList);
    }

    @PostMapping
    public RP addScrapeConfig(@RequestBody PromeScrapeConfigDto promeScrapeConfigDto) throws Exception{
        scrapeConfigService.addScrapeConfig(promeScrapeConfigDto);
        return RP.buildSuccess("添加指标数据源配置成功");
    }

    @PutMapping
    public RP updateScrapeConfig(@RequestBody PromeScrapeConfigDto promeScrapeConfigDto) throws Exception{
        scrapeConfigService.updateScrapeConfig(promeScrapeConfigDto);
        return RP.buildSuccess("修改指标数据源配置成功");
    }

    @DeleteMapping("/{jobName}")
    public RP deleteScrapeConfig(@PathVariable("jobName")String jobName) throws Exception{
        scrapeConfigService.deleteScrapeConfig(jobName);
        return RP.buildSuccess("删除指标数据源配置成功");
    }
}
