package com.mbhit.kyancafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class KyanCafeApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(KyanCafeApplication.class, args);
	}
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        return builder.sources(KyanCafeApplication.class);
	    }
}
