package com.mhkim.tms.controller.v1.room.dto;

import com.mhkim.tms.entity.room.Room;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

public class RoomDto {

    @EqualsAndHashCode(callSuper = false)
    @Relation(itemRelation = "room", collectionRelation = "rooms")
    @Getter
    @Setter
    @ToString
    public static class Response extends RepresentationModel<Response> {

        @ApiModelProperty(value = "숙소 ID")
        private Long roomIdx;

        @ApiModelProperty(value = "숙소명")
        private String name;

        @ApiModelProperty(value = "방개수")
        private String count;

        @ApiModelProperty(value = "체크인 시간")
        private String checkIn;

        @ApiModelProperty(value = "체크아웃 시간")
        private String checkOut;

        public Response(Room room) {
            this.roomIdx = room.getRoomIdx();
            this.name = room.getName();
            this.count = room.getCount();
            this.checkIn = room.getCheckIn();
            this.checkOut = room.getCheckOut();
        }

    }

    @Getter
    @Setter
    @ToString
    public static class Request {

        @ApiModelProperty(value = "숙소명", required = true)
        private String name;

        @ApiModelProperty(value = "방개수", required = true)
        private String count;

        @ApiModelProperty(value = "체크인 시간", required = true)
        private String checkIn;

        @ApiModelProperty(value = "체크아웃 시간", required = true)
        private String checkOut;

        public Room toEntity() {
            return Room.builder()
                    .name(name)
                    .count(count)
                    .checkIn(checkIn)
                    .checkOut(checkOut)
                    .build();
        }

    }

}
