/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mkyong.helloworld.config;

import com.mkyong.helloworld.vault.VaultProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Zifan Wang - zifwang@expedia.com
 */

@Configuration
@RefreshScope
@Import(RefreshAutoConfiguration.class)
public class RefreshConfig {

    @Bean
    @RefreshScope
    public VaultProperty vaultProperty(@Value("${immutable-property}") String test,
                                       @Value("${test-property}") String refreshable) {
        return new VaultProperty(test, refreshable);
    }
}