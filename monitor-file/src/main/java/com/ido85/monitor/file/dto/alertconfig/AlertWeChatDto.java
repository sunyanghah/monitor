package com.ido85.monitor.file.dto.alertconfig;

import lombok.Data;

/**
 * Created by dell on 2019/4/23.
 * @author dell
 */
@Data
public class AlertWeChatDto {

    private boolean send_resolved = false;

    /**
     * 企业微信 prometheus应用secret
     */
    private String api_secret;

    /**
     * https://qyapi.weixin.qq.com/cgi-bin/
     */
    private String api_url = "https://qyapi.weixin.qq.com/cgi-bin/";

    /**
     * 企业微信 企业id
     */
    private String corp_id;

    private String message;

    /**
     * 企业微信 prometheus应用agent_id
     */
    private String agent_id;

    private String to_user;

    /**
     * 企业微信 组id ，别忘了设置企业微信的prometheus应用设置该组可见范围
     */
    private String to_party;

    private String to_tag;

}
