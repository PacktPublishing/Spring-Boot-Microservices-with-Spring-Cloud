package com.company.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.company.Controllers.InterceptorExample;

@Component	
public class CustomWebConfig implements WebMvcConfigurer{
	   @Autowired
	   InterceptorExample interceptorExample;

	   @Override
	   public void addInterceptors(InterceptorRegistry registry) {
	      registry.addInterceptor(interceptorExample).addPathPatterns("/hello/**");
	   }
}
