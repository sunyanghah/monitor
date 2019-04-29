package com.ido85.monitor.file.dto.promeconfig;

import com.ido85.monitor.file.dto.promebase.BasicAuthDto;
import com.ido85.monitor.file.dto.promebase.TlsConfigDto;
import com.ido85.monitor.file.dto.scrapeconfig.ScrapeConfigDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2019/4/19.
 * @author dell
 */
@Data
public class PromeScrapeConfigDto {

    /**
     * 指标抓取工作名称
     */
    @NotBlank
    private String job_name;

    /**
     * 抓取间隔
     */
    private String scrape_interval = "1m";

    /**
     * 抓取超时时间
     */
    private String scrape_timeout = "10s";

    /**
     * 指标抓取url
     */
    private String metrics_path = "/metrics";

    private boolean honor_labels = false;

    private boolean honor_timestamps = true;

    /**
     * 抓取协议
     */
    private String scheme = "http";

    private Map<String,Object> params;

    private BasicAuthDto basic_auth;

    private String bearer_token;

    private TlsConfigDto tls_config;

    private String proxy_url;

    private List<ScrapeConfigDto> static_configs;


    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        if (!(obj instanceof PromeScrapeConfigDto)){
            return false;
        }
        PromeScrapeConfigDto scrapeConfigDto = (PromeScrapeConfigDto) obj;
        return this.getJob_name().equals(scrapeConfigDto.getJob_name());
    }
}
