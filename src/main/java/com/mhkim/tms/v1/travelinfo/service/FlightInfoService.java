package com.mhkim.tms.v1.travelinfo.service;

import com.mhkim.tms.v1.travelinfo.controller.dto.FlightInfoItemDto;
import com.mhkim.tms.v1.travelinfo.controller.dto.FlightInfoItemsDto;
import com.mhkim.tms.v1.travelinfo.entity.FlightInfo;
import com.mhkim.tms.v1.travelinfo.repository.FlightInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class FlightInfoService {

    private final FlightInfoRepository flightInfoRepository;
    private final FlightInfoRequestService flightInfoRequestService;

    public void syncFlightInfo() {

        flightInfoRequestService.requestFlightInfo(1).subscribe(flightInfo -> {
            log.debug("flightInfo: {}", flightInfo);

            int total = flightInfo.getTotalCount();
            int numOfRows = flightInfo.getNumOfRows();
            int maxPage = total / numOfRows;
            if (total % numOfRows > 0)
                maxPage++;

            Flux<FlightInfoItemsDto> flightInfoItems = Flux.range(1, maxPage).flatMap(pageNo -> {
                log.debug("pageNo: {}", pageNo);
                return flightInfoRequestService.requestFlightInfo(pageNo);
            });

            flightInfoItems.subscribe(items ->
                    items.getFlightInfoItems().forEach(item -> {
                        log.debug("item: {}", items.toString());
                        addFlightInfo(item);
                    })
            );
        });
    }

    public Optional<FlightInfo> addFlightInfo(FlightInfoItemDto item) {
        FlightInfo flightInfo = FlightInfo.builder()
                .airlineNm(item.getAirlineNm())
                .arrAirportNm(item.getArrAirportNm())
                .arrPlandTime(item.getArrPlandTime())
                .depAirportNm(item.getDepAirportNm())
                .depPlandTime(item.getDepPlandTime())
                .vihicleId(item.getVihicleId())
                .build();

        return Optional.of(flightInfoRepository.save(flightInfo));
    }

}
