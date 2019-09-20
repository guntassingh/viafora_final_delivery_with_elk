package com.rsystems.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = { "classpath:messages.properties" })
public class AppConfig {

	
}
