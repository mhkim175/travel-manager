package com.mhkim.tms.v1.booking.repository;

import com.mhkim.tms.v1.booking.entity.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightBookingRepository extends JpaRepository<FlightBooking, Long> {

    public List<FlightBooking> findAllByUserUserIdx(Long userIdx);

}
