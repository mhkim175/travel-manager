package com.mhkim.tms.v1.booking.repository;

import com.mhkim.tms.v1.booking.entity.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomBookingRepository extends JpaRepository<RoomBooking, Long> {

    public List<RoomBooking> findAllByUserUserIdx(Long userIdx);

}
