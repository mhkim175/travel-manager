package com.mhkim.tms.service.room;

import com.mhkim.tms.entity.room.Room;
import com.mhkim.tms.exception.error.AlreadyExistsException;
import com.mhkim.tms.exception.error.NotFoundException;
import com.mhkim.tms.repository.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    public Room getRoom(Long roomIdx) {
        return roomRepository.findById(roomIdx)
                .orElseThrow(() -> new NotFoundException(Room.class, roomIdx));
    }

    @Transactional
    public Room addRoom(Room roomRequest) {
        roomRepository.findByName(roomRequest.getName())
                .ifPresent(room -> {
                    throw new AlreadyExistsException(Room.class, roomRequest.getName());
                });

        return save(roomRequest);
    }

    @Transactional
    public Room deleteRoom(Long roomIdx) {
        return roomRepository.findById(roomIdx)
                .map(room -> {
                    roomRepository.deleteById(room.getRoomIdx());
                    return room;
                })
                .orElseThrow(() -> new NotFoundException(Room.class, roomIdx));
    }

    private Room save(Room room) {
        return roomRepository.save(room);
    }
    
}
