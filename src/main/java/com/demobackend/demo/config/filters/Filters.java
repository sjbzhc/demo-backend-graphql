package com.demobackend.demo.config.filters;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class Filters {
    @Autowired
    private final RestFilter restFilter;
    @Autowired
    private final LoginFilter loginFilter;

//    @Bean
//    public FilterRegistrationBean loginRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(loginFilter);
//        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/login/*"));
//        return filterRegistrationBean;
//    }

    @Bean
    public FilterRegistrationBean restRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(restFilter);
        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/graphql"));
        return filterRegistrationBean;
    }
}
