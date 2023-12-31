package com.example.WetherAPI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
public class ApplicationProperties {

    @Value("${whether.openapi}")
    private String openWhetherAPI;

    @Value("${webservice.httpConnection.timeout}")
    private int	httpConnectionTimeOut;

    @Value("${webservice.httpSocket.timeout}")
    private int	httpSocketTimeOut;

    public int getHttpConnectionTimeOut() {
        return httpConnectionTimeOut;
    }

    public void setHttpConnectionTimeOut(int httpConnectionTimeOut) {
        this.httpConnectionTimeOut = httpConnectionTimeOut;
    }

    public String getOpenWhetherAPI() {
        return openWhetherAPI;
    }

    public int getHttpSocketTimeOut() {
        return httpSocketTimeOut;
    }

    public void setHttpSocketTimeOut(int httpSocketTimeOut) {
        this.httpSocketTimeOut = httpSocketTimeOut;
    }
}
