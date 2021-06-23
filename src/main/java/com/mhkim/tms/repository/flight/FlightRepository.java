package com.mhkim.tms.repository.flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhkim.tms.entity.flight.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
