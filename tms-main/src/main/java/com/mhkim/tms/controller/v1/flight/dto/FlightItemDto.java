package com.mhkim.tms.controller.v1.flight.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mhkim.tms.entity.flight.Flight;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightItemDto {

    private String airlineNm;
    private String arrAirportNm;
    private String arrPlandTime;
    private String depAirportNm;
    private String depPlandTime;
    private String vihicleId;

    public Flight toEntity() {
        return Flight.builder()
                .airlineNm(airlineNm)
                .arrAirportNm(arrAirportNm)
                .arrPlandTime(arrPlandTime)
                .depAirportNm(depAirportNm)
                .depPlandTime(depPlandTime)
                .vihicleId(vihicleId)
                .build();
    }

}
