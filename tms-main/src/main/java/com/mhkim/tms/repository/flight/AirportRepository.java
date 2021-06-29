package com.mhkim.tms.repository.flight;

import com.mhkim.tms.entity.flight.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    Optional<Airport> findByAirportId(String airportId);

}
