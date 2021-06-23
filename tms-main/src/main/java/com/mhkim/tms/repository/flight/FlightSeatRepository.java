package com.mhkim.tms.repository.flight;

import com.mhkim.tms.entity.flight.FlightSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightSeatRepository extends JpaRepository<FlightSeat, Long> {
}
