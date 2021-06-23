package com.mhkim.tms.repository.room;

import com.mhkim.tms.entity.room.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomBookingRepository extends JpaRepository<RoomBooking, Long> {

    public List<RoomBooking> findAllByUserUserIdx(Long userIdx);

}
