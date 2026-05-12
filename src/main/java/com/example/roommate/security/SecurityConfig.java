package com.example.roommate.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    private final JwtFiltro jwtFiltro;

    public SecurityConfig(JwtFiltro jwtFiltro) {
        this.jwtFiltro = jwtFiltro;
    }

    @Bean
    public FilterRegistrationBean<JwtFiltro> registraFiltro() {
        FilterRegistrationBean<JwtFiltro> bean = new FilterRegistrationBean<>();
        bean.setFilter(jwtFiltro);
        bean.addUrlPatterns("/*");
        return bean;
    }
}
