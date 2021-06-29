package com.mhkim.tms.service.flight;

import com.mhkim.tms.entity.flight.FlightSeat;
import com.mhkim.tms.exception.error.AlreadyExistsException;
import com.mhkim.tms.exception.error.NotFoundException;
import com.mhkim.tms.repository.flight.FlightSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FlightSeatService {

    private final FlightSeatRepository flightSeatRepository;

    public List<FlightSeat> getFlightSeats() {
        return flightSeatRepository.findAll();
    }

    public FlightSeat getFlightSeat(Long flightSeatIdx) {
        return flightSeatRepository.findById(flightSeatIdx)
                .orElseThrow(() -> new NotFoundException(FlightSeat.class, flightSeatIdx));
    }

    @Transactional
    public FlightSeat addFlightSeat(FlightSeat flightSeatRequest) {
        flightSeatRepository.findBySeatNameAndFlightFlightIdx(flightSeatRequest.getSeatName()
                , flightSeatRequest.getFlight().getFlightIdx())
                .ifPresent(flightSeat -> {
                    throw new AlreadyExistsException(FlightSeat.class, flightSeatRequest.getSeatName()
                            , flightSeatRequest.getFlight().getFlightIdx());
                });

        return save(flightSeatRequest);
    }

    @Transactional
    public FlightSeat deleteFlightSeat(Long flightSeatIdx) {
        return flightSeatRepository.findById(flightSeatIdx)
                .map(flightSeat -> {
                    flightSeatRepository.deleteById(flightSeat.getFlightSeatIdx());
                    return flightSeat;
                })
                .orElseThrow(() -> new NotFoundException(FlightSeat.class, flightSeatIdx));
    }

    private FlightSeat save(FlightSeat flightSeat) {
        return flightSeatRepository.save(flightSeat);
    }

}
