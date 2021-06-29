package com.mhkim.tms.controller.v1.flight.dto;

import com.mhkim.tms.entity.flight.Airline;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

public class AirlineDto {

    @EqualsAndHashCode(callSuper = false)
    @Relation(itemRelation = "airline", collectionRelation = "airlines")
    @Getter
    @Setter
    @ToString
    public static class Response extends RepresentationModel<Response> {

        @ApiModelProperty(value = "항공사 ID")
        private Long airlineIdx;

        @ApiModelProperty(value = "항공사고유아이디")
        private String airlineId;

        @ApiModelProperty(value = "항공사명")
        private String airlineNm;

        public Response(Airline flight) {
            this.airlineIdx = flight.getAirlineIdx();
            this.airlineId = flight.getAirlineId();
            this.airlineNm = flight.getAirlineNm();
        }

    }

    @Getter
    @Setter
    @ToString
    public static class Request {

        @ApiModelProperty(value = "항공사고유아이디", required = true)
        private String airlineId;

        @ApiModelProperty(value = "항공사명", required = true)
        private String airlineNm;

        public Airline toEntity() {
            return Airline.builder()
                    .airlineId(airlineId)
                    .airlineNm(airlineNm)
                    .build();
        }

    }

}
