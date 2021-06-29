package com.mhkim.tms.repository.flight;

import com.mhkim.tms.entity.flight.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {

    Optional<Airline> findByAirlineId(String airlineId);

}
