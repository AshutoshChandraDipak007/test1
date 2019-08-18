package com.tutorialspoint.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.tutorialspoint.myproject.filter.ErrorFilter;
import com.tutorialspoint.myproject.filter.PostFilter;
import com.tutorialspoint.myproject.filter.PreFilter;
import com.tutorialspoint.myproject.filter.RouterFilter;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class EmployeeZuulServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeZuulServiceApplication.class, args);
	}
	
	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}

	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}

	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}

	@Bean
	public RouterFilter routeFilter() {
		return new RouterFilter();
	}
}
