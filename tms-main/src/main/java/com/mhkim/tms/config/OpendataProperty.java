package com.mhkim.tms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@ConfigurationProperties(prefix = "opendata")
@Getter
@Setter
@ToString
public class OpendataProperty {

    private String baseUrl;
    private String busServiceUrl;
    private String flightServiceUrl;
    private String shipServiceUrl;
    private String serviceKey;
    private String numOfRows;

}
