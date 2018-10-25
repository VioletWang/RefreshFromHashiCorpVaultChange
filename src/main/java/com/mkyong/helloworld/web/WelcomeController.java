package com.mkyong.helloworld.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mkyong.helloworld.service.HelloWorldService;

@Controller
public class WelcomeController {

	private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	private final HelloWorldService helloWorldService;

	@Autowired
	public WelcomeController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@Autowired
	ContextRefresher contextRefresher;

	@Autowired
	@Qualifier("refreshedSecretMap")
	MutablePropertySources refreshedSecretMap;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Map<String, Object> model) {

		logger.debug("index() is executed!");
		logger.info(refreshedSecretMap.toString());

		model.put("msg1", helloWorldService.getImmutableValue());
		model.put("msg2", helloWorldService.getRefreshedValue());
		
		return "index";
	}

	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	public ModelAndView refresh() {

		logger.debug("refresh() is executed");

		contextRefresher.refresh();
		ModelAndView model = new ModelAndView();
		model.setViewName("index");

		return model;
	}

}