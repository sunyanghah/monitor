package com.ido85.monitor.file.dto.alertconfig;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2019/4/23.
 * @author dell
 */
public class AlertRouteDto {

    @Getter
    @Setter
    private String receiver;

    @Getter
    @Setter
    private String group_wait = "30s";

    @Getter
    @Setter
    private String group_interval = "5m";

    @Getter
    @Setter
    private String repeat_interval = "1h";

    @Getter
    @Setter
    private List<String> group_by;

    @Getter
    @Setter
    private Map<String,String> match_re;

    @Getter
    @Setter
    private Map<String,String> match;

    private boolean continueField = false;

    @Getter
    @Setter
    private List<AlertRouteDto> routes;

    public void setContinue(boolean continueField) {
        this.continueField = continueField;
    }

    public boolean getContinue(){
        return this.continueField;
    }
}
