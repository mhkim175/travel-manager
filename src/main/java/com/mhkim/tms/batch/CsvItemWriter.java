package com.mhkim.tms.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import com.mhkim.tms.v1.travelinfo.entity.RoomInfo;
import com.mhkim.tms.v1.travelinfo.service.RoomInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class CsvItemWriter implements ItemWriter<RoomInfo> {

    private final RoomInfoService roomInfoService;
    
    @Override
    public void write(List<? extends RoomInfo> items) throws Exception {
        items.forEach(item -> {
            log.debug("item: {}", item.toString());
            roomInfoService.addRoomInfo(item);
        });
    }

}
