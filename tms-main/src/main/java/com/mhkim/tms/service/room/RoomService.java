package com.mhkim.tms.service.room;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mhkim.tms.entity.room.Room;
import com.mhkim.tms.repository.room.RoomRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public Optional<Room> addRoom(Room room) {
        return Optional.of(roomRepository.save(room));
    }

}
