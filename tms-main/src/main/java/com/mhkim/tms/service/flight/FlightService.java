package com.mhkim.tms.service.flight;

import com.mhkim.tms.controller.v1.flight.dto.FlightItemDto;
import com.mhkim.tms.controller.v1.flight.dto.FlightItemsDto;
import com.mhkim.tms.entity.flight.Flight;
import com.mhkim.tms.repository.flight.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightInfoRequestService flightInfoRequestService;

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
                        addFlight(item);
                    })
            );
        });
    }

    public Optional<Flight> addFlight(FlightItemDto item) {
        Flight flight = Flight.builder()
                .airlineNm(item.getAirlineNm())
                .arrAirportNm(item.getArrAirportNm())
                .arrPlandTime(item.getArrPlandTime())
                .depAirportNm(item.getDepAirportNm())
                .depPlandTime(item.getDepPlandTime())
                .vihicleId(item.getVihicleId())
                .build();

        return Optional.of(flightRepository.save(flight));
    }

}
