package com.mhkim.tms.repository.flight;

import com.mhkim.tms.entity.flight.FlightSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightSeatRepository extends JpaRepository<FlightSeat, Long> {

    Optional<FlightSeat> findBySeatNameAndFlightFlightIdx(String seatName, Long flightIdx);

}
