package com.mhkim.tms.controller.v1.flight.dto;

import com.mhkim.tms.entity.flight.Flight;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

public class FlightDto {

    @EqualsAndHashCode(callSuper = false)
    @Relation(itemRelation = "flight", collectionRelation = "flights")
    @Getter
    @Setter
    @ToString
    public static class Response extends RepresentationModel<Response> {

        @ApiModelProperty(value = "항공편 ID")
        private Long flightIdx;

        @ApiModelProperty(value = "항공사명")
        private String airlineNm;

        @ApiModelProperty(value = "출발 공항")
        private String arrAirportNm;

        @ApiModelProperty(value = "출발 시간")
        private String arrPlandTime;

        @ApiModelProperty(value = "도착 공항")
        private String depAirportNm;

        @ApiModelProperty(value = "도착 시간")
        private String depPlandTime;

        @ApiModelProperty(value = "항공편명")
        private String vihicleId;

        public Response(Flight flight) {
            this.flightIdx = flight.getFlightIdx();
            this.airlineNm = flight.getAirlineNm();
            this.arrAirportNm = flight.getArrAirportNm();
            this.arrPlandTime = flight.getArrPlandTime();
            this.depAirportNm = flight.getDepAirportNm();
            this.depPlandTime = flight.getDepPlandTime();
            this.vihicleId = flight.getVihicleId();
        }

    }

    @Getter
    @Setter
    @ToString
    public static class Request {

        @ApiModelProperty(value = "항공사명", required = true)
        private String airlineNm;

        @ApiModelProperty(value = "출발 공항", required = true)
        private String arrAirportNm;

        @ApiModelProperty(value = "출발 시간", required = true)
        private String arrPlandTime;

        @ApiModelProperty(value = "도착 공항", required = true)
        private String depAirportNm;

        @ApiModelProperty(value = "도착 시간", required = true)
        private String depPlandTime;

        @ApiModelProperty(value = "항공편명", required = true)
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

}
