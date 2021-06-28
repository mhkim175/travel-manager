package com.mhkim.tms.repository.bus;

import com.mhkim.tms.entity.bus.Bus;
import com.mhkim.tms.entity.bus.BusBooking;
import com.mhkim.tms.entity.room.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusBookingRepository extends JpaRepository<BusBooking, Long> {

    public List<BusBooking> findAllByUserUserIdx(Long userIdx);

}
