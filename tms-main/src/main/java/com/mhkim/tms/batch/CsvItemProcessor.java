package com.mhkim.tms.batch;

import com.mhkim.tms.controller.v1.room.dto.RoomItemDto;
import com.mhkim.tms.entity.room.Room;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CsvItemProcessor implements ItemProcessor<RoomItemDto, Room> {

    @Override
    public Room process(RoomItemDto item) throws Exception {
        return Room.builder()
                .name(item.getName())
                .count(item.getCount())
                .checkIn(item.getCheckIn())
                .checkOut(item.getCheckOut())
                .build();
    }

}
