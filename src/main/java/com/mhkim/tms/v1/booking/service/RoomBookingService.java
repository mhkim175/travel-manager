package com.mhkim.tms.v1.booking.service;

import com.mhkim.tms.advice.exception.CDataNotFoundException;
import com.mhkim.tms.v1.booking.entity.RoomBooking;
import com.mhkim.tms.v1.booking.repository.RoomBookingRepository;
import com.mhkim.tms.v1.travelinfo.entity.Room;
import com.mhkim.tms.v1.travelinfo.repository.RoomRepository;
import com.mhkim.tms.v1.user.entity.User;
import com.mhkim.tms.v1.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoomBookingService {

    private final RoomBookingRepository roomBookingRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public List<RoomBooking> getRoomBookingList() {
        return roomBookingRepository.findAll();
    }

    public Optional<RoomBooking> getRoomBooking(Long roomBookId) {
        return roomBookingRepository.findById(roomBookId);
    }

    public List<RoomBooking> getRoomBookingByUserId(Long userId) {
        return roomBookingRepository.findAllByUserUserId(userId);
    }

    @Transactional
    public RoomBooking bookRoom(LocalDate bookDate, Long roomId, Long userId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new CDataNotFoundException("Room not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CDataNotFoundException("User not found"));

        return roomBookingRepository.save(
                RoomBooking.builder()
                        .bookDate(bookDate)
                        .room(room)
                        .user(user)
                        .build()
        );
    }

    @Transactional
    public RoomBooking deleteRoomBooking(Long roomBookId) {
        return getRoomBooking(roomBookId)
                .map(roomBooking -> {
                    roomBookingRepository.deleteById(roomBooking.getRoomBookId());
                    return roomBooking;
                }).orElseThrow(() -> new CDataNotFoundException("RoomBooking not found"));
    }

}