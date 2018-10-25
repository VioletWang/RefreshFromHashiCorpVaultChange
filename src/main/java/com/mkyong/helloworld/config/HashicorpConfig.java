/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mkyong.helloworld.config;

import com.expedia.payments.vault.authenticator.AwsAccessKeyClientAuthenticator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.config.AbstractVaultConfiguration;
import org.springframework.vault.core.VaultTemplate;

import java.util.Map;

/**
 * @author Zifan Wang - zifwang@expedia.com
 */
@Configuration
public class HashicorpConfig extends AbstractVaultConfiguration{

    private final Logger logger = LoggerFactory.getLogger(HashicorpConfig.class);

    private final static String VAULT_STS_ENDPOINT="https://hto7jgd97d.execute-api.us-west-2.amazonaws.com/dev/";
    private final static String VAULT_URI="vault.lab.karmalabaws.net";
    private final static Integer VAULT_PORT=443;
    private final static String VAULT_SECRET_PATH="secret/poc/local";
    private final static String VAULT_SECRET_PATH_REFRESHABLE="secret/poc/refresh";

    @Override
    public VaultEndpoint vaultEndpoint() {
        return VaultEndpoint.create(VAULT_URI,VAULT_PORT);
    }

    @Override
    public ClientAuthentication clientAuthentication() {
       return AwsAccessKeyClientAuthenticator.builder()
               .withStsAuthEndpoint(VAULT_STS_ENDPOINT)
               .build();
    }

    @Bean (name = "applicationSecretMap")
    public MutablePropertySources applicationSecretMap() {
        logger.info("Creating application secret map");
        MutablePropertySources mutablePropertySources = ((AbstractEnvironment) this.getEnvironment()).getPropertySources();
        Map<String, Object> secretMap = vaultTemplate().read(VAULT_SECRET_PATH).getData();
        MapPropertySource mapPropertySource = new MapPropertySource("applicationSecretMap", secretMap);
        mutablePropertySources.addFirst(mapPropertySource);
        return mutablePropertySources;
    }

    @Bean (name = "refreshedSecretMap")
    @RefreshScope
    public MutablePropertySources refreshedSecretMap() {
        logger.info("Creating/Refreshing refreshed secret map");
        MutablePropertySources mutablePropertySources = ((AbstractEnvironment) this.getEnvironment()).getPropertySources();
        Map<String, Object> secretMap = vaultTemplate().read(VAULT_SECRET_PATH_REFRESHABLE).getData();
        MapPropertySource mapPropertySource = new MapPropertySource("refreshArgsSecretMap", secretMap);
        mutablePropertySources.addFirst(mapPropertySource);
        return mutablePropertySources;
    }
}
