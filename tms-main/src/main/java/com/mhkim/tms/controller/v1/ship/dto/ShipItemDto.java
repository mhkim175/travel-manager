package com.mhkim.tms.controller.v1.ship.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mhkim.tms.entity.ship.Ship;
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

    public Ship toEntity() {
        return Ship.builder()
                .vihicleNm(vihicleNm)
                .depPlaceNm(depPlaceNm)
                .arrPlaceNm(arrPlaceNm)
                .depPlandTime(depPlandTime)
                .arrPlandTime(arrPlandTime)
                .charge(charge)
                .build();
    }


}
