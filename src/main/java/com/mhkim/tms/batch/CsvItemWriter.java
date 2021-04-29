package com.mhkim.tms.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import com.mhkim.tms.v1.travelinfo.entity.Room;
import com.mhkim.tms.v1.travelinfo.service.RoomService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class CsvItemWriter implements ItemWriter<Room> {

    private final RoomService roomService;
    
    @Override
    public void write(List<? extends Room> items) throws Exception {
        items.forEach(item -> {
            log.debug("item: {}", item.toString());
            roomService.addRoom(item);
        });
    }

}
