package com.mhkim.tms.repository.room;

import com.mhkim.tms.entity.room.RoomBooking;
import com.mhkim.tms.entity.ship.ShipBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoomBookingRepository extends JpaRepository<RoomBooking, Long> {

    public List<RoomBooking> findAllByUserUserIdx(Long userIdx);

    public Optional<RoomBooking> findByRoomRoomIdxAndUserUserIdxAndBookDate(Long roomIdx, Long userIdx, LocalDate bookDate);

}
