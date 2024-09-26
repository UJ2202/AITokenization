package com.uj.aitokenization.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.uj.aitokenization.factory.TokenizerFactory;

@Configuration
@ComponentScan(basePackages = {"com.uj.aitokenization"})
public class TokenizerConfiguration {
	@Bean
	public FactoryBean aiTokenizerSearchableFactoryBean() {
		ServiceLocatorFactoryBean factoryBean= new ServiceLocatorFactoryBean();
		factoryBean.setServiceLocatorInterface(TokenizerFactory.class);
		return factoryBean;
	}
	

}
