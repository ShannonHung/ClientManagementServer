package org.prlad.clientmanagement.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("adminhome");
        registry.addViewController("/").setViewName("adminhome");
        registry.addViewController("/login").setViewName("login");
    }
}