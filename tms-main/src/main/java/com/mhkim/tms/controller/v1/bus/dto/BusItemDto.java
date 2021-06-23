package com.mhkim.tms.controller.v1.bus.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusItemDto {

    private String routeId;
    private String gradeNm;
    private String depPlandTime;
    private String arrPlandTime;
    private String depPlaceNm;
    private String arrPlaceNm;
    private String charge;

}
