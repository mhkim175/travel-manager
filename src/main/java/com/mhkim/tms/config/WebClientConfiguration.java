package com.mhkim.tms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.DefaultUriBuilderFactory.EncodingMode;

@Configuration
public class WebClientConfiguration {

    @Bean
    public WebClient tagoWebClient(OpendataProp opendataProp) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(opendataProp.getBaseUrl());
        factory.setEncodingMode(EncodingMode.VALUES_ONLY);

        return WebClient.builder().uriBuilderFactory(factory)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
