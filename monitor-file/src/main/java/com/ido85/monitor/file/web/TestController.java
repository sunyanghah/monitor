package com.ido85.monitor.file.web;

import com.ido85.monitor.common.platform.RP;
import com.ido85.monitor.file.util.ConfigUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * Created by dell on 2019/4/17.
 * @author dell
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public RP test() throws Exception{
        TestDto testDto = new TestDto();
        testDto.setName(null);
        testDto.setAge(19);
        testDto.setAddress(null);
        return RP.buildSuccess(testDto);
    }

    public static void main(String[] args) {
        TestDto testDto = new TestDto();
        testDto.setName(null);
        testDto.setAge(19);
        testDto.setAddress(null);
        TestDto testDto2 = new TestDto();
        testDto2.setName("张三");
        testDto2.setAddress(null);
        testDto2.setAge(23);
        testDto.setList(Arrays.asList(testDto2));
        Object object = ConfigUtil.handleNoNull(testDto);
        System.out.println(object);

        String str = "{\"age\":23,\"name\":\"张三\",\"test\":\"2342\",\"address\":null}";
        Object object2 = ConfigUtil.handleNoNull(str);
        System.out.println(object2);
    }

}
