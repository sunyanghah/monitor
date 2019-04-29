package com.ido85.monitor.manager.web;

import com.alibaba.fastjson.JSON;
import com.ido85.monitor.common.platform.RP;
import com.ido85.monitor.manager.dto.InWebHookDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dell on 2019/4/27.
 * @author dell
 */
@RestController
@Slf4j
public class WebHookController {

    @PostMapping("/webhook")
    public RP webhook(@RequestBody InWebHookDto inWebHookDto) throws Exception{
        log.info("---------------------------------------------------------------------------");
        log.info(JSON.toJSONString(inWebHookDto));
        log.info("---------------------------------------------------------------------------");
        return RP.buildSuccess("");
    }

}
