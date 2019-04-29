package com.ido85.monitor.file.web;

import com.ido85.monitor.common.platform.RP;
import com.ido85.monitor.file.dto.promebase.InConfigSourceDto;
import com.ido85.monitor.file.dto.promeconfig.PromeConfigDto;
import com.ido85.monitor.file.service.PromeConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by dell on 2019/4/17.
 * @author dell
 */
@RestController
@RequestMapping("/prome")
public class PromeConfigController {

    @Autowired
    private PromeConfigService promeConfigService;

    /**
     * 获取prometheus源配置
     * @return
     * @throws Exception
     */
    @GetMapping("/source")
    public RP getPromeConfigSource() throws Exception{
        return RP.buildSuccess("",promeConfigService.getPromeConfigSource());
    }

    /**
     * 获取prometheus配置
     * @return
     * @throws Exception
     */
    @GetMapping
    public RP getPromeConfig() throws Exception{
        return RP.buildSuccess(promeConfigService.getPromeConfig());
    }

    /**
     * 修改prometheus配置
     * @param inConfigSourceDto
     * @return
     * @throws Exception
     */
    @PutMapping("/source")
    public RP updatePromeConfigSource(@RequestBody @Valid InConfigSourceDto inConfigSourceDto) throws Exception{
        promeConfigService.updatePromeConfigSource(inConfigSourceDto.getConfigContent());
        return RP.buildSuccess("配置修改成功");
    }

    /**
     * 修改prometheus配置
     * @param promeConfigDto
     * @return
     * @throws Exception
     */
    @PutMapping
    public RP updatePromeConfig(@RequestBody PromeConfigDto promeConfigDto) throws Exception{
        promeConfigService.updatePromeConfig(promeConfigDto);
        return RP.buildSuccess("配置修改成功");
    }

}
