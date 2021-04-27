package com.mhkim.tms.v1.travelinfo.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightInfoItemDto {

    private String airlineNm;
    private String arrAirportNm;
    private String arrPlandTime;
    private String depAirportNm;
    private String depPlandTime;
    private String vihicleId;

}
