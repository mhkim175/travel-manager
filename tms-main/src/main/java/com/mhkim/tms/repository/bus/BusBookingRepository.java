package com.mhkim.tms.repository.bus;

import com.mhkim.tms.entity.bus.Bus;
import com.mhkim.tms.entity.bus.BusBooking;
import com.mhkim.tms.entity.flight.FlightBooking;
import com.mhkim.tms.entity.room.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BusBookingRepository extends JpaRepository<BusBooking, Long> {

    public List<BusBooking> findAllByUserUserIdx(Long userIdx);

    public Optional<BusBooking> findByBusBusIdxAndUserUserIdxAndBookDate(Long shipIdx, Long userIdx, LocalDate bookDate);

}
