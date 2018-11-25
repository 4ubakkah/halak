package com.halak.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
//TODO get rid of
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/");
        registry.addViewController("/h2-console").setViewName("h2-console");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/swagger-ui").setViewName("swagger-ui");
    }

}