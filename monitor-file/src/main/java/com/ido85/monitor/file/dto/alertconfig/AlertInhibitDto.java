package com.ido85.monitor.file.dto.alertconfig;

import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2019/4/28.
 * @author dell
 */
@Data
public class AlertInhibitDto {

    private Map<String,String> source_match;

    private Map<String,String> source_match_re;

    private Map<String,String> target_match;

    private Map<String,String> target_match_re;

    private List<String> equal;

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        if (!(obj instanceof AlertInhibitDto)){
            return false;
        }
        AlertInhibitDto objDto = (AlertInhibitDto) obj;

        return super.equals(obj);
    }

    private String handleEqu(AlertInhibitDto dto) throws Exception{
        Class c = dto.getClass();
        for (Method method : c.getMethods()){
            if (method.getName().startsWith("get")){
                method.invoke(dto);
            }
        }
        return null;
    }
}
