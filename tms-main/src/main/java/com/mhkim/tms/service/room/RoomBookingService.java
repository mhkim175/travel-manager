package com.mhkim.tms.service.room;

import com.mhkim.tms.entity.room.RoomBooking;
import com.mhkim.tms.exception.error.AlreadyExistsException;
import com.mhkim.tms.exception.error.NotFoundException;
import com.mhkim.tms.repository.room.RoomBookingRepository;
import com.mhkim.tms.repository.room.RoomRepository;
import com.mhkim.tms.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomBookingService {

    private final RoomBookingRepository roomBookingRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public List<RoomBooking> getRoomBookings() {
        return roomBookingRepository.findAll();
    }

    public RoomBooking getRoomBooking(Long roomBookingIdx) {
        return roomBookingRepository.findById(roomBookingIdx)
                .orElseThrow(() -> new NotFoundException(RoomBooking.class, roomBookingIdx));
    }

    @Transactional
    public RoomBooking addRoomBooking(RoomBooking roomBookingRequest) {
        roomBookingRepository.findByRoomRoomIdxAndUserUserIdxAndBookDate(roomBookingRequest.getRoom().getRoomIdx(),
                roomBookingRequest.getUser().getUserIdx(), roomBookingRequest.getBookDate())
                .ifPresent(roomBooking -> {
                    throw new AlreadyExistsException(RoomBooking.class, roomBookingRequest.getRoom().getRoomIdx(),
                            roomBookingRequest.getUser().getUserIdx(), roomBookingRequest.getBookDate());
                });

        return save(roomBookingRequest);
    }

    @Transactional
    public RoomBooking deleteRoomBooking(Long roomBookingIdx) {
        return roomBookingRepository.findById(roomBookingIdx)
                .map(roomBooking -> {
                    roomBookingRepository.deleteById(roomBooking.getRoomBookIdx());
                    return roomBooking;
                })
                .orElseThrow(() -> new NotFoundException(RoomBooking.class, roomBookingIdx));
    }

    private RoomBooking save(RoomBooking roomBooking) {
        return roomBookingRepository.save(roomBooking);
    }

}
