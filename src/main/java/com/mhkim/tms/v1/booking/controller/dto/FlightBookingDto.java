package com.mhkim.tms.v1.booking.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mhkim.tms.v1.booking.entity.FlightBooking;
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
        private Long flightBookId;

        @ApiModelProperty(value = "항공편 ID", required = true)
        private Long flightId;

        @ApiModelProperty(value = "예약자 ID", required = true)
        private Long userId;

    }

    @Getter
    @Setter
    @ToString
    public static class Response {

        @ApiModelProperty(value = "항공편 예약 ID", required = true)
        private String flightBookId;

        @ApiModelProperty(value = "항공편 예약 날짜", required = true)
        private String bookDate;

        @ApiModelProperty(value = "항공편 ID", required = true)
        private Long flightId;

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
        private Long userId;

        @ApiModelProperty(value = "예약자 이메일")
        private String email;

        @ApiModelProperty(value = "예약자명")
        private String name;

        public Response(FlightBooking source) {
            BeanUtils.copyProperties(source, this);

            this.flightId = source.getFlight().getFlightId();
            this.airlineNm = source.getFlight().getAirlineNm();
            this.arrAirportNm = source.getFlight().getArrAirportNm();
            this.arrPlandTime = source.getFlight().getArrPlandTime();
            this.depAirportNm = source.getFlight().getDepAirportNm();
            this.depPlandTime = source.getFlight().getDepPlandTime();
            this.vihicleId = source.getFlight().getVihicleId();
            this.userId = source.getUser().getUserId();
            this.email = source.getUser().getEmail();
            this.name = source.getUser().getName();
        }

    }

    @Getter
    @Setter
    @ToString
    public static class Book {

        @JsonFormat(pattern = "yyyy-MM-dd")
        @ApiModelProperty(value = "항공편 날짜", notes = "yyyy-MM-dd", example = "2021-01-01")
        private LocalDate bookDate;

        @ApiModelProperty(value = "항공편 ID", required = true)
        private Long flightId;

        @ApiModelProperty(value = "예약자 ID", required = true)
        private Long userId;

    }

}
