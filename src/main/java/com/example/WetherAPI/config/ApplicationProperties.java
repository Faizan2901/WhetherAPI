package com.example.WetherAPI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ApplicationProperties {

    @Value("${whether.openapi}")
    private String openWhetherAPI;

    public int getHttpConnectionTimeOut() {
        return httpConnectionTimeOut;
    }

    public void setHttpConnectionTimeOut(int httpConnectionTimeOut) {
        this.httpConnectionTimeOut = httpConnectionTimeOut;
    }

    @Value("${webservice.httpConnection.timeout}")
    private int	httpConnectionTimeOut;
    public String getOpenWhetherAPI() {
        return openWhetherAPI;
    }

    public int getHttpSocketTimeOut() {
        return httpSocketTimeOut;
    }

    public void setHttpSocketTimeOut(int httpSocketTimeOut) {
        this.httpSocketTimeOut = httpSocketTimeOut;
    }

    @Value("${webservice.httpSocket.timeout}")
    private int	httpSocketTimeOut;
}
