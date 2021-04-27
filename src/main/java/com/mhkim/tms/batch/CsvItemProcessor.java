package com.mhkim.tms.batch;

import com.mhkim.tms.v1.travelinfo.controller.dto.RoomInfoItemDto;
import com.mhkim.tms.v1.travelinfo.entity.RoomInfo;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CsvItemProcessor implements ItemProcessor<RoomInfoItemDto, RoomInfo> {

    @Override
    public RoomInfo process(RoomInfoItemDto item) throws Exception {
        return RoomInfo.builder()
                .name(item.getName())
                .count(item.getCount())
                .checkIn(item.getCheckIn())
                .checkOut(item.getCheckOut())
                .build();
    }

}
