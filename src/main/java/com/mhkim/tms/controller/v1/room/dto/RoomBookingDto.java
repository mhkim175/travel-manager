package com.mhkim.tms.controller.v1.room.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mhkim.tms.entity.room.RoomBooking;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

public class RoomBookingDto {

    @Getter
    @Setter
    @ToString
    public static class Request {

        @ApiModelProperty(value = "숙소 예약 ID", required = true)
        private Long roomBookIdx;

        @ApiModelProperty(value = "숙소 ID", required = true)
        private Long roomIdx;

        @ApiModelProperty(value = "예약자 ID", required = true)
        private Long userIdx;

    }

    @Getter
    @Setter
    @ToString
    public static class Response {

        @ApiModelProperty(value = "숙소 예약 ID", required = true)
        private String roomBookIdx;

        @ApiModelProperty(value = "숙소 ID", required = true)
        private Long roomIdx;

        @ApiModelProperty(value = "숙소명")
        private String roomName;

        @ApiModelProperty(value = "숙소 체크인")
        private String checkIn;

        @ApiModelProperty(value = "숙소 체크아웃")
        private String checkOut;

        @ApiModelProperty(value = "예약자 ID", required = true)
        private Long userIdx;

        @ApiModelProperty(value = "예약자 이메일")
        private String email;

        @ApiModelProperty(value = "예약자명")
        private String name;

        @ApiModelProperty(value = "숙소 예약 날짜", required = true)
        private String bookDate;

        public Response(RoomBooking source) {
            BeanUtils.copyProperties(source, this);

            this.roomIdx = source.getRoom().getRoomIdx();
            this.roomName = source.getRoom().getName();
            this.checkIn = source.getRoom().getCheckIn();
            this.checkOut = source.getRoom().getCheckOut();
            this.userIdx = source.getUser().getUserIdx();
            this.email = source.getUser().getEmail();
            this.name = source.getUser().getName();
        }

    }

    @Getter
    @Setter
    @ToString
    public static class Book {

        @ApiModelProperty(value = "숙소 ID", required = true)
        private Long roomIdx;

        @ApiModelProperty(value = "예약자 ID", required = true)
        private Long userIdx;

        @JsonFormat(pattern = "yyyy-MM-dd")
        @ApiModelProperty(value = "예약 날짜", notes = "yyyy-MM-dd", example = "2021-01-01")
        private LocalDate bookDate;

    }

}
