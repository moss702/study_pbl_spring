package com.ikkikki.demo.config;

import com.ikkikki.demo.config.filter.CorsFilter;
import com.ikkikki.demo.config.filter.EncodeFilter;
import com.ikkikki.demo.config.listener.ContextPathListener;
import com.ikkikki.demo.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.ServletContextListener;

@Configuration
public class ServletConfig {

  @Bean
  public FilterRegistrationBean<EncodeFilter> encodeFilterRegi(EncodeFilter encodeFilter) {
    FilterRegistrationBean<EncodeFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(encodeFilter);
    registrationBean.addUrlPatterns("/*");
    registrationBean.setOrder(1);
    return registrationBean;
  }

  @Bean
  public FilterRegistrationBean<CorsFilter> corsFilterRegi(CorsFilter corsFilter) {
    FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(corsFilter);
    registrationBean.addUrlPatterns("/*");
    registrationBean.setOrder(2);
    return registrationBean;
  }
}