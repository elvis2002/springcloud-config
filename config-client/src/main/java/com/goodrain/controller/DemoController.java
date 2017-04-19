package com.goodrain.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


@EnableAutoConfiguration
@RefreshScope
@RestController
public class DemoController {

	@Value("${from}")
    String from;
	
	
    @RequestMapping("/from")
    public String from() {
        return this.from;
    }
}