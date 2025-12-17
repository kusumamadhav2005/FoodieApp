package com.example.FoodieApp;

import com.example.FoodieApp.filter.Jwtfilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FoodieAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodieAppApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<Jwtfilter> jwtFilterMethod() {

		FilterRegistrationBean<Jwtfilter> filterRegistrationBean =
				new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new Jwtfilter());
		filterRegistrationBean.addUrlPatterns("/api/v1/*");

		return filterRegistrationBean;
	}
}
