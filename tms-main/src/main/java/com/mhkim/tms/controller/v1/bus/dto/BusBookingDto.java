package com.mhkim.tms.controller.v1.bus.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mhkim.tms.entity.bus.Bus;
import com.mhkim.tms.entity.bus.BusBooking;
import com.mhkim.tms.entity.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;

public class BusBookingDto {

    @EqualsAndHashCode(callSuper = false)
    @Relation(itemRelation = "busBooking", collectionRelation = "busBookings")
    @Getter
    @Setter
    @ToString
    public static class Response extends RepresentationModel<Response> {

        @ApiModelProperty(value = "버스 예약 ID")
        private Long busBookIdx;

        @ApiModelProperty(value = "버스 ID")
        private Long busIdx;

        @ApiModelProperty(value = "예약자 ID")
        private Long userIdx;

        @ApiModelProperty(value = "예약 시간")
        private LocalDate bookDate;

        public Response(BusBooking busBooking) {
            this.busBookIdx = busBooking.getBusBookIdx();
            this.busIdx = busBooking.getBus().getBusIdx();
            this.userIdx = busBooking.getUser().getUserIdx();
            this.bookDate = busBooking.getBookDate();
        }

    }

    @Getter
    @Setter
    @ToString
    public static class Request {

        @ApiModelProperty(value = "버스 ID", required = true)
        private Long busIdx;

        @ApiModelProperty(value = "예약자 ID", required = true)
        private Long userIdx;

        @JsonFormat(pattern = "yyyy-MM-dd")
        @ApiModelProperty(value = "예약 날짜", notes = "yyyy-MM-dd", example = "2021-01-01", required = true)
        private LocalDate bookDate;

        public BusBooking toEntity() {
            return BusBooking.builder()
                    .bus(new Bus(busIdx))
                    .user(new User(userIdx))
                    .bookDate(bookDate)
                    .build();
        }

    }

}
