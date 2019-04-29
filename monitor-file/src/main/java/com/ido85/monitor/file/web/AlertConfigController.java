package com.ido85.monitor.file.web;

import com.ido85.monitor.common.platform.RP;
import com.ido85.monitor.file.dto.alertconfig.AlertConfigDto;
import com.ido85.monitor.file.dto.alertconfig.AlertGlobalDto;
import com.ido85.monitor.file.dto.alertconfig.AlertInhibitDto;
import com.ido85.monitor.file.dto.alertconfig.AlertRouteDto;
import com.ido85.monitor.file.dto.promebase.InConfigSourceDto;
import com.ido85.monitor.file.service.AlertConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2019/4/19.
 * @author dell
 */
@RestController
@RequestMapping("/alert")
public class AlertConfigController {

    @Autowired
    private AlertConfigService alertConfigService;

    @GetMapping("/source")
    public RP getAlertConfigSource() throws Exception{
        return RP.buildSuccess("",alertConfigService.getAlertConfigSource());
    }

    @GetMapping
    public RP getAlertConfig() throws Exception{
        return RP.buildSuccess(alertConfigService.getAlertConfig());
    }

    @PutMapping("/source")
    public RP updateAlertConfigSource(@RequestBody @Valid InConfigSourceDto inConfigSourceDto) throws Exception{


        alertConfigService.updateAlertConfigSource(inConfigSourceDto.getConfigContent());
        return RP.buildSuccess("报警方式修改成功");
    }

    @PutMapping
    public RP updateAlertConfig(@RequestBody AlertConfigDto alertConfigDto) throws Exception{
        alertConfigService.updateAlertConfig(alertConfigDto);
        return RP.buildSuccess("报警方式修改成功");
    }

    @GetMapping("/global")
    public RP getAlertGlobal() throws Exception{
        return RP.buildSuccess(alertConfigService.getAlertConfig().getGlobal());
    }

    @PutMapping("/global")
    public RP updateAlertGlobal(@RequestBody AlertGlobalDto alertGlobal) throws Exception{
        alertConfigService.updateAlertGlobal(alertGlobal);
        return RP.buildSuccess("修改配置成功");
    }

    @GetMapping("/route")
    public RP getAlertRoute() throws Exception{
        return RP.buildSuccess(alertConfigService.getAlertConfig().getRoute());
    }

    @PutMapping("/route")
    public RP updateAlertRoute(@RequestBody AlertRouteDto alertRouteDto) throws Exception{
        alertConfigService.updateAlertRoute(alertRouteDto);
        return RP.buildSuccess("修改配置成功");
    }


    @GetMapping("/receiver")
    public RP getAlertReceiver() throws Exception{
        return RP.buildSuccess(alertConfigService.getAlertConfig().getReceivers());
    }

    @PostMapping("/receiver")
    public RP addAlertReceiver(@RequestBody Map<String,Object> alertReceiver) throws Exception{
        alertConfigService.addAlertReceiver(alertReceiver);
        return RP.buildSuccess("新增报警接收者成功");
    }

    @PutMapping("/receiver")
    public RP updateAlertReceiver(@RequestBody Map<String,Object> alertReceiver) throws Exception{
        alertConfigService.updateAlertReceiver(alertReceiver);
        return RP.buildSuccess("修改报警接收者成功");
    }

    @DeleteMapping("/receiver/{name}")
    public RP deleteAlertReceiver(@PathVariable("name")String name) throws Exception{
        alertConfigService.deleteAlertReceiver(name);
        return RP.buildSuccess("删除报警接收者成功");
    }


    @GetMapping("/inhibition")
    public RP getAlertInhibit() throws Exception{
        return RP.buildSuccess(alertConfigService.getAlertConfig().getInhibit_rules());
    }


    @PutMapping("/inhibition")
    public RP updateAlertInhibit(@RequestBody List<AlertInhibitDto> alertInhibitDtoList) throws Exception{
        alertConfigService.updateAlertInhibit(alertInhibitDtoList);
        return RP.buildSuccess("修改抑制规则成功");
    }
}
