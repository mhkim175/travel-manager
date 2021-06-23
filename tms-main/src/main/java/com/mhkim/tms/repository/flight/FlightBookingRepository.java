package com.mhkim.tms.repository.flight;

import com.mhkim.tms.entity.flight.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightBookingRepository extends JpaRepository<FlightBooking, Long> {

    public List<FlightBooking> findAllByUserUserIdx(Long userIdx);

}
