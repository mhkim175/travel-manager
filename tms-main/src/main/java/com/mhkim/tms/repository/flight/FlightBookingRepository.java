package com.mhkim.tms.repository.flight;

import com.mhkim.tms.entity.flight.FlightBooking;
import com.mhkim.tms.entity.ship.ShipBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FlightBookingRepository extends JpaRepository<FlightBooking, Long> {

    public List<FlightBooking> findAllByUserUserIdx(Long userIdx);

    public Optional<FlightBooking> findByFlightSeatFlightSeatIdxAndUserUserIdxAndBookDate(Long flightSeatIdx, Long userIdx, LocalDate bookDate);

}
