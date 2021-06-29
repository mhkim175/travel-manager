package com.mhkim.tms.service.flight;

import com.mhkim.tms.entity.flight.Airport;
import com.mhkim.tms.exception.error.AlreadyExistsException;
import com.mhkim.tms.exception.error.NotFoundException;
import com.mhkim.tms.repository.flight.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public List<Airport> getAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirport(Long airportIdx) {
        return airportRepository.findById(airportIdx)
                .orElseThrow(() -> new NotFoundException(Airport.class, airportIdx));
    }

    @Transactional
    public Airport addAirport(Airport airportRequest) {
        airportRepository.findByAirportId(airportRequest.getAirportId())
                .ifPresent(airport -> {
                    throw new AlreadyExistsException(Airport.class, airportRequest.getAirportId());
                });

        return save(airportRequest);
    }

    @Transactional
    public Airport deleteAirport(Long airportIdx) {
        return airportRepository.findById(airportIdx)
                .map(airport -> {
                    airportRepository.deleteById(airport.getAirportIdx());
                    return airport;
                })
                .orElseThrow(() -> new NotFoundException(Airport.class, airportIdx));
    }

    private Airport save(Airport airport) {
        return airportRepository.save(airport);
    }

}
