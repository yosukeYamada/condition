package com.example.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORSの設定を行うクラス.
 * 
 * @author yuichiyasui
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private EnvironmentsConfiguration envConfig;

    /**
     * CORSの許可設定を行うメソッド
     * 
     * @param registry CORSの許可一覧みたいな
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(envConfig.getOriginUrl());
    }

}
