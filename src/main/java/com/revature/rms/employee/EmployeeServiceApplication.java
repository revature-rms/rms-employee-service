package com.revature.rms.employee;

import com.revature.rms.core.config.EurekaInstanceConfigBeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class EmployeeServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}
	@Bean
	public EurekaInstanceConfigBeanPostProcessor eurekaInstanceConfigBeanPostProcessor(){
		return new EurekaInstanceConfigBeanPostProcessor();
	}
}
