package com.mhkim.tms.controller.v1.flight.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mhkim.tms.entity.flight.FlightBooking;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

public class FlightBookingDto {

    @Getter
    @Setter
    @ToString
    public static class Request {

        @ApiModelProperty(value = "항공편 예약 ID", required = true)
        private Long flightBookIdx;

        @ApiModelProperty(value = "항공편 좌석 ID", required = true)
        private Long flightSeatIdx;

        @ApiModelProperty(value = "예약자 ID", required = true)
        private Long userIdx;

    }

    @Getter
    @Setter
    @ToString
    public static class Response {

        @ApiModelProperty(value = "항공편 예약 ID", required = true)
        private String flightBookIdx;

        @ApiModelProperty(value = "항공편 ID", required = true)
        private Long flightIdx;

        @ApiModelProperty(value = "항공편 좌석 ID", required = true)
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

        @ApiModelProperty(value = "예약자 ID", required = true)
        private Long userIdx;

        @ApiModelProperty(value = "예약자 이메일")
        private String email;

        @ApiModelProperty(value = "예약자명")
        private String name;

        @ApiModelProperty(value = "항공편 예약 날짜", required = true)
        private String bookDate;

        public Response(FlightBooking source) {
            BeanUtils.copyProperties(source, this);

            this.flightSeatIdx = source.getFlightSeat().getFlightSeatIdx();
            this.flightIdx = source.getFlightSeat().getFlight().getFlightIdx();
            this.airlineNm = source.getFlightSeat().getFlight().getAirlineNm();
            this.arrAirportNm = source.getFlightSeat().getFlight().getArrAirportNm();
            this.arrPlandTime = source.getFlightSeat().getFlight().getArrPlandTime();
            this.depAirportNm = source.getFlightSeat().getFlight().getDepAirportNm();
            this.depPlandTime = source.getFlightSeat().getFlight().getDepPlandTime();
            this.vihicleId = source.getFlightSeat().getFlight().getVihicleId();
            this.userIdx = source.getUser().getUserIdx();
            this.email = source.getUser().getEmail();
            this.name = source.getUser().getName();
        }

    }

    @Getter
    @Setter
    @ToString
    public static class Book {

        @ApiModelProperty(value = "항공편 좌석 ID", required = true)
        private Long flightSeatIdx;

        @ApiModelProperty(value = "예약자 ID", required = true)
        private Long userIdx;

        @JsonFormat(pattern = "yyyy-MM-dd")
        @ApiModelProperty(value = "항공편 날짜", notes = "yyyy-MM-dd", example = "2021-01-01")
        private LocalDate bookDate;

    }

}
