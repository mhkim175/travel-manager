package com.mhkim.tms.service.flight;

import com.mhkim.tms.controller.v1.flight.dto.FlightItemsDto;
import com.mhkim.tms.entity.flight.Flight;
import com.mhkim.tms.exception.error.AlreadyExistsException;
import com.mhkim.tms.exception.error.NotFoundException;
import com.mhkim.tms.repository.flight.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightInfoRequestService flightInfoRequestService;

    public List<Flight> getFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlight(Long flightIdx) {
        return flightRepository.findById(flightIdx)
                .orElseThrow(() -> new NotFoundException(Flight.class, flightIdx));
    }

    @Transactional
    public Flight addFlight(Flight flightRequest) {
        flightRepository.findByVihicleId(flightRequest.getVihicleId())
                .ifPresent(flight -> {
                    throw new AlreadyExistsException(Flight.class, flightRequest.getVihicleId());
                });

        return save(flightRequest);
    }

    @Transactional
    public Flight deleteFlight(Long flightIdx) {
        return flightRepository.findById(flightIdx)
                .map(flight -> {
                    flightRepository.deleteById(flight.getFlightIdx());
                    return flight;
                })
                .orElseThrow(() -> new NotFoundException(Flight.class, flightIdx));
    }
    
    public void syncFlight() {

        flightInfoRequestService.requestFlightInfo(1).subscribe(flight -> {
            int total = flight.getTotalCount();
            int numOfRows = flight.getNumOfRows();
            int maxPage = total / numOfRows;
            if (total % numOfRows > 0) maxPage++;

            Flux<FlightItemsDto> flightItems = Flux.range(1, maxPage).flatMap(pageNo -> {
                return flightInfoRequestService.requestFlightInfo(pageNo);
            });

            flightItems.subscribe(items ->
                    items.getFlightItems().forEach(item -> {
                        log.debug("item: {}", items.toString());
                        save(item.toEntity());
                    })
            );
        });
    }

    private Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

}
