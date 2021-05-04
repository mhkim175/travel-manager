package com.mhkim.tms.v1.travelinfo.repository.flight;

import com.mhkim.tms.v1.travelinfo.entity.flight.Flight;
import com.mhkim.tms.v1.travelinfo.entity.flight.FlightSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightSeatRepository extends JpaRepository<FlightSeat, Long> {
}
