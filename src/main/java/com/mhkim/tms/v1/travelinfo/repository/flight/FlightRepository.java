package com.mhkim.tms.v1.travelinfo.repository.flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhkim.tms.v1.travelinfo.entity.flight.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
