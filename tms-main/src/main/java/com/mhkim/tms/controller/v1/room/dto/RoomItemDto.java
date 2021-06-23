package com.mhkim.tms.controller.v1.room.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoomItemDto {

    private String name;
    private String count;
    private String checkIn;
    private String checkOut;

}
