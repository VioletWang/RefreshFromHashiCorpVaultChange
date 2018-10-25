package com.mkyong.helloworld.service;

import com.mkyong.helloworld.vault.VaultProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class HelloWorldService {

	private static final Logger logger = LoggerFactory.getLogger(HelloWorldService.class);

	@Autowired
	private VaultProperty vaultProperty;

	public String getImmutableValue() {
		logger.debug("getImmutableValue() is executed!");

		for(int i = 0; i < 100; i++) {
			int randomNum = ThreadLocalRandom.current().nextInt(100);
			logger.debug(Integer.toString(randomNum));
		}

		return vaultProperty.getTest();
	}

	public String getRefreshedValue() {
		logger.debug("getRefreshedValue() is executed!");
		return vaultProperty.getRefreshable();
	}

}