package com.example.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 環境変数を参照するクラス.
 * 
 * @author yuichi
 *
 */
@Component
@ConfigurationProperties(prefix = "environments")
public class EnvironmentsConfiguration {

    /** CORSを許可するオリジンのURL */
    private String originUrl;

    public String getOriginUrl() {
        return originUrl;
    }
    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }
}