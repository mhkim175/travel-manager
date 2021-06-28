package com.mhkim.tms.controller.v1.bus.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.mhkim.tms.entity.bus.Bus;
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

    public Bus toEntity() {
        return Bus.builder()
                .routeId(routeId)
                .gradeNm(gradeNm)
                .depPlandTime(depPlandTime)
                .arrPlandTime(arrPlandTime)
                .depPlaceNm(depPlaceNm)
                .arrPlaceNm(arrPlaceNm)
                .charge(charge)
                .build();
    }

}
