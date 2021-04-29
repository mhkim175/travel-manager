package com.mhkim.tms.v1.travelinfo.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipItemDto {

    private String vihicleNm;
    private String depPlaceNm;
    private String arrPlaceNm;
    private String depPlandTime;
    private String arrPlandTime;
    private String charge;

}
