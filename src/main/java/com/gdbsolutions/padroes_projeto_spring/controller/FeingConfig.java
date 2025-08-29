package com.gdbsolutions.padroes_projeto_spring.controller;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeingConfig {
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                requestTemplate.header("x-api-key","Bearer" + getApiKey());
            }
        };
    }

    private String getApiKey() {
        return "946261ab74227dca0929a4e194a05ca6ad550edf9eb77e4bc89321850c069582";
    }
}
