package com.mhkim.tms.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

import com.mhkim.tms.v1.travelinfo.dto.RoomInfoDto;
import com.mhkim.tms.v1.travelinfo.entity.RoomInfo;

@Configuration
public class CsvItemProcessor implements ItemProcessor<RoomInfoDto, RoomInfo> {

    @Override
    public RoomInfo process(RoomInfoDto item) throws Exception {
        return RoomInfo.builder()
                .name(item.getName())
                .count(item.getCount())
                .checkIn(item.getCheckIn())
                .checkOut(item.getCheckOut())
                .build();
    }

}
