package com.mhkim.tms.controller.v1.room.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mhkim.tms.entity.room.Room;
import com.mhkim.tms.entity.room.RoomBooking;
import com.mhkim.tms.entity.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;

public class RoomBookingDto {

    @EqualsAndHashCode(callSuper = false)
    @Relation(itemRelation = "roomBooking", collectionRelation = "roomBookings")
    @Getter
    @Setter
    @ToString
    public static class Response extends RepresentationModel<Response> {


        @ApiModelProperty(value = "숙소 예약 ID")
        private Long roomBookIdx;

        @ApiModelProperty(value = "숙소 ID")
        private Long roomIdx;

        @ApiModelProperty(value = "숙소명")
        private String roomName;

        @ApiModelProperty(value = "숙소 체크인")
        private String checkIn;

        @ApiModelProperty(value = "숙소 체크아웃")
        private String checkOut;

        @ApiModelProperty(value = "예약자 ID")
        private Long userIdx;

        @ApiModelProperty(value = "예약자 이메일")
        private String email;

        @ApiModelProperty(value = "예약자명")
        private String name;

        @ApiModelProperty(value = "숙소 예약 날짜")
        private LocalDate bookDate;

        public Response(RoomBooking roomBooking) {
            this.roomBookIdx = roomBooking.getRoomBookIdx();
            this.roomIdx = roomBooking.getRoom().getRoomIdx();
            this.roomName = roomBooking.getRoom().getName();
            this.checkIn = roomBooking.getRoom().getCheckIn();
            this.checkOut = roomBooking.getRoom().getCheckOut();
            this.userIdx = roomBooking.getUser().getUserIdx();
            this.email = roomBooking.getUser().getEmail();
            this.name = roomBooking.getUser().getName();
            this.bookDate = roomBooking.getBookDate();
        }

    }

    @Getter
    @Setter
    @ToString
    public static class Request {

        @ApiModelProperty(value = "숙소 ID", required = true)
        private Long roomIdx;

        @ApiModelProperty(value = "예약자 ID", required = true)
        private Long userIdx;

        @JsonFormat(pattern = "yyyy-MM-dd")
        @ApiModelProperty(value = "예약 날짜", notes = "yyyy-MM-dd", example = "2021-01-01", required = true)
        private LocalDate bookDate;

        public RoomBooking toEntity() {
            return RoomBooking.builder()
                    .room(new Room(roomIdx))
                    .user(new User(userIdx))
                    .bookDate(bookDate)
                    .build();
        }

    }

}
