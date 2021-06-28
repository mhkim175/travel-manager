package com.mhkim.tms.controller.v1.flight.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mhkim.tms.entity.flight.FlightBooking;
import com.mhkim.tms.entity.flight.FlightSeat;
import com.mhkim.tms.entity.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;

public class FlightBookingDto {

    @EqualsAndHashCode(callSuper = false)
    @Relation(itemRelation = "flightBooking", collectionRelation = "flightBookings")
    @Getter
    @Setter
    @ToString
    public static class Response extends RepresentationModel<Response> {

        @ApiModelProperty(value = "항공편 예약 ID")
        private Long flightBookIdx;

        @ApiModelProperty(value = "항공편 ID")
        private Long flightIdx;

        @ApiModelProperty(value = "항공편 좌석 ID")
        private Long flightSeatIdx;

        @ApiModelProperty(value = "항공사명")
        private String airlineNm;

        @ApiModelProperty(value = "출발공항")
        private String arrAirportNm;

        @ApiModelProperty(value = "출발시간")
        private String arrPlandTime;

        @ApiModelProperty(value = "도착공항")
        private String depAirportNm;

        @ApiModelProperty(value = "도착시간")
        private String depPlandTime;

        @ApiModelProperty(value = "항공편명")
        private String vihicleId;

        @ApiModelProperty(value = "예약자 ID")
        private Long userIdx;

        @ApiModelProperty(value = "예약자 이메일")
        private String email;

        @ApiModelProperty(value = "예약자명")
        private String name;

        @ApiModelProperty(value = "항공편 예약 날짜")
        private LocalDate bookDate;

        public Response(FlightBooking flightBooking) {
            this.flightBookIdx = flightBooking.getFlightBookIdx();
            this.flightIdx = flightBooking.getFlightSeat().getFlight().getFlightIdx();
            this.flightSeatIdx = flightBooking.getFlightSeat().getFlightSeatIdx();
            this.airlineNm = flightBooking.getFlightSeat().getFlight().getAirlineNm();
            this.arrAirportNm = flightBooking.getFlightSeat().getFlight().getArrAirportNm();
            this.arrPlandTime = flightBooking.getFlightSeat().getFlight().getArrPlandTime();
            this.depAirportNm = flightBooking.getFlightSeat().getFlight().getDepAirportNm();
            this.depPlandTime = flightBooking.getFlightSeat().getFlight().getDepPlandTime();
            this.vihicleId = flightBooking.getFlightSeat().getFlight().getVihicleId();
            this.userIdx = flightBooking.getUser().getUserIdx();
            this.email = flightBooking.getUser().getEmail();
            this.name = flightBooking.getUser().getName();
            this.bookDate = flightBooking.getBookDate();
        }

    }

    @Getter
    @Setter
    @ToString
    public static class Request {

        @ApiModelProperty(value = "항공편 좌석 ID", required = true)
        private Long flightSeatIdx;

        @ApiModelProperty(value = "예약자 ID", required = true)
        private Long userIdx;

        @JsonFormat(pattern = "yyyy-MM-dd")
        @ApiModelProperty(value = "항공편 날짜", notes = "yyyy-MM-dd", example = "2021-01-01", required = true)
        private LocalDate bookDate;

        public FlightBooking toEntity() {
            return FlightBooking.builder()
                    .flightSeat(new FlightSeat(flightSeatIdx))
                    .user(new User(userIdx))
                    .bookDate(bookDate)
                    .build();
        }

    }

}
