package com.mhkim.tms.repository.flight;

import com.mhkim.tms.entity.flight.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    Optional<Flight> findByVihicleId(String vihicleId);

}
