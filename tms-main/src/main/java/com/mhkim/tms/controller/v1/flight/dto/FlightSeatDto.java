package com.mhkim.tms.controller.v1.flight.dto;

import com.mhkim.tms.entity.flight.Flight;
import com.mhkim.tms.entity.flight.FlightSeat;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

public class FlightSeatDto {

    @EqualsAndHashCode(callSuper = false)
    @Relation(itemRelation = "flightSeat", collectionRelation = "flightSeats")
    @Getter
    @Setter
    @ToString
    public static class Response extends RepresentationModel<Response> {

        @ApiModelProperty(value = "항공편 좌석 ID")
        private Long flightSeatIdx;

        @ApiModelProperty(value = "항공편 좌석명")
        private String seatName;

        @ApiModelProperty(value = "항공편 좌석 등급")
        private String classCode;

        @ApiModelProperty(value = "항공편 ID")
        private Long flightIdx;

        public Response(FlightSeat flightSeat) {
            this.flightSeatIdx = flightSeat.getFlightSeatIdx();
            this.seatName = flightSeat.getSeatName();
            this.classCode = flightSeat.getClassCode();
            this.flightIdx = flightSeat.getFlight().getFlightIdx();
        }

    }

    @Getter
    @Setter
    @ToString
    public static class Request {

        @ApiModelProperty(value = "항공편 좌석명")
        private String seatName;

        @ApiModelProperty(value = "항공편 좌석 등급")
        private String classCode;

        @ApiModelProperty(value = "항공편 ID")
        private Long flightIdx;

        public FlightSeat toEntity() {
            return FlightSeat.builder()
                    .seatName(seatName)
                    .classCode(classCode)
                    .flight(new Flight(flightIdx))
                    .build();
        }

    }

}
