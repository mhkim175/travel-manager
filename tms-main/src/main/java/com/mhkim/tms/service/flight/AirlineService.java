package com.mhkim.tms.service.flight;

import com.mhkim.tms.entity.flight.Airline;
import com.mhkim.tms.exception.error.AlreadyExistsException;
import com.mhkim.tms.exception.error.NotFoundException;
import com.mhkim.tms.repository.flight.AirlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AirlineService {

    private final AirlineRepository airlineRepository;

    public List<Airline> getAirlines() {
        return airlineRepository.findAll();
    }

    public Airline getAirline(Long airlineIdx) {
        return airlineRepository.findById(airlineIdx)
                .orElseThrow(() -> new NotFoundException(Airline.class, airlineIdx));
    }

    @Transactional
    public Airline addAirline(Airline airlineRequest) {
        airlineRepository.findByAirlineId(airlineRequest.getAirlineId())
                .ifPresent(airline -> {
                    throw new AlreadyExistsException(Airline.class, airlineRequest.getAirlineId());
                });

        return save(airlineRequest);
    }

    @Transactional
    public Airline deleteAirline(Long airlineIdx) {
        return airlineRepository.findById(airlineIdx)
                .map(airline -> {
                    airlineRepository.deleteById(airline.getAirlineIdx());
                    return airline;
                })
                .orElseThrow(() -> new NotFoundException(Airline.class, airlineIdx));
    }

    private Airline save(Airline airline) {
        return airlineRepository.save(airline);
    }

}
