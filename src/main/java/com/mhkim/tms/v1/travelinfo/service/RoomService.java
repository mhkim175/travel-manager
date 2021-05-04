package com.mhkim.tms.v1.travelinfo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mhkim.tms.v1.travelinfo.entity.room.Room;
import com.mhkim.tms.v1.travelinfo.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public Optional<Room> addRoom(Room room) {
        return Optional.of(roomRepository.save(room));
    }

}
