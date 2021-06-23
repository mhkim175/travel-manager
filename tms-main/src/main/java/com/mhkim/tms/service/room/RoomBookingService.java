package com.mhkim.tms.service.room;

import com.mhkim.tms.advice.exception.CDataNotFoundException;
import com.mhkim.tms.entity.room.RoomBooking;
import com.mhkim.tms.repository.room.RoomBookingRepository;
import com.mhkim.tms.entity.room.Room;
import com.mhkim.tms.repository.room.RoomRepository;
import com.mhkim.tms.entity.user.User;
import com.mhkim.tms.repository.user.UserRepository;
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

    public Optional<RoomBooking> getRoomBooking(Long roomBookIds) {
        return roomBookingRepository.findById(roomBookIds);
    }

    public List<RoomBooking> getRoomBookingByUserId(Long userIdx) {
        return roomBookingRepository.findAllByUserUserIdx(userIdx);
    }

    @Transactional
    public RoomBooking bookRoom(LocalDate bookDate, Long roomIdx, Long userIdx) {
        Room room = roomRepository.findById(roomIdx)
                .orElseThrow(() -> new CDataNotFoundException("Room not found"));
        User user = userRepository.findById(userIdx)
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
    public RoomBooking deleteRoomBooking(Long roomBookIdx) {
        return getRoomBooking(roomBookIdx)
                .map(roomBooking -> {
                    roomBookingRepository.deleteById(roomBooking.getRoomBookIdx());
                    return roomBooking;
                }).orElseThrow(() -> new CDataNotFoundException("RoomBooking not found"));
    }

}
