package com.mhkim.tms.controller.v1.flight.dto;

import com.mhkim.tms.entity.flight.Airport;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

public class AirportDto {

    @EqualsAndHashCode(callSuper = false)
    @Relation(itemRelation = "airport", collectionRelation = "airports")
    @Getter
    @Setter
    @ToString
    public static class Response extends RepresentationModel<Response> {

        @ApiModelProperty(value = "공항 ID")
        private Long airportIdx;

        @ApiModelProperty(value = "공항고유아이디")
        private String airportId;

        @ApiModelProperty(value = "공항명")
        private String airportNm;

        public Response(Airport airport) {
            this.airportIdx = airport.getAirportIdx();
            this.airportId = airport.getAirportId();
            this.airportNm = airport.getAirportNm();
        }

    }

    @Getter
    @Setter
    @ToString
    public static class Request {

        @ApiModelProperty(value = "공항고유아이디", required = true)
        private String airportId;

        @ApiModelProperty(value = "공항명", required = true)
        private String airportNm;

        public Airport toEntity() {
            return Airport.builder()
                    .airportId(airportId)
                    .airportNm(airportNm)
                    .build();
        }

    }

}
