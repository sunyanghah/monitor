package com.ido85.monitor.auth.config;

import com.ido85.monitor.auth.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

/**
 * Created by dell on 2018/11/12.
 */
public class CustomClientService implements ClientDetailsService {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        CustomClientDetails customClientDetails = new CustomClientDetails();
        customClientDetails.setSysOauthClientDetails(clientMapper.selectById(clientId));
        return customClientDetails;
    }
}
