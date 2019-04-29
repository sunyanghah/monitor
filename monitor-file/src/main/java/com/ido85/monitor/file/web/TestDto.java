package com.ido85.monitor.file.web;

import lombok.Data;

import java.util.List;

/**
 * Created by dell on 2019/4/25.
 */
@Data
public class TestDto {

    private String name;

    private String address;

    private int age;

    private List<TestDto> list;


    public String getTest() {
        return "2342";
    }

}
