package com.mhkim.tms.controller.v1.ship.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mhkim.tms.entity.ship.Ship;
import com.mhkim.tms.entity.ship.ShipBooking;
import com.mhkim.tms.entity.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;

public class ShipBookingDto {

    @EqualsAndHashCode(callSuper = false)
    @Relation(itemRelation = "shipBooking", collectionRelation = "shipBookings")
    @Getter
    @Setter
    @ToString
    public static class Response extends RepresentationModel<Response> {

        @ApiModelProperty(value = "선박 예약 ID")
        private Long shipBookIdx;

        @ApiModelProperty(value = "선박 ID")
        private Long shipIdx;

        @ApiModelProperty(value = "예약자 ID")
        private Long userIdx;

        @ApiModelProperty(value = "선박 예약 날짜")
        private LocalDate bookDate;

        public Response(ShipBooking shipBooking) {
            this.shipBookIdx = shipBooking.getShipBookIdx();
            this.shipIdx = shipBooking.getShip().getShipIdx();
            this.userIdx = shipBooking.getUser().getUserIdx();
            this.bookDate = shipBooking.getBookDate();
        }

    }

    @Getter
    @Setter
    @ToString
    public static class Request {

        @ApiModelProperty(value = "선박 ID", required = true)
        private Long shipIdx;

        @ApiModelProperty(value = "예약자 ID", required = true)
        private Long userIdx;

        @JsonFormat(pattern = "yyyy-MM-dd")
        @ApiModelProperty(value = "예약 날짜", notes = "yyyy-MM-dd", example = "2021-01-01", required = true)
        private LocalDate bookDate;

        public ShipBooking toEntity() {
            return ShipBooking.builder()
                    .ship(new Ship(shipIdx))
                    .user(new User(userIdx))
                    .bookDate(bookDate)
                    .build();
        }

    }

}
