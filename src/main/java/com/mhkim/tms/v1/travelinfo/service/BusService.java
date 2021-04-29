package com.mhkim.tms.v1.travelinfo.service;

import com.mhkim.tms.v1.travelinfo.controller.dto.BusItemDto;
import com.mhkim.tms.v1.travelinfo.controller.dto.BusItemsDto;
import com.mhkim.tms.v1.travelinfo.entity.Bus;
import com.mhkim.tms.v1.travelinfo.repository.BusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BusService {

    private final BusRepository busRepository;
    private final BusInfoRequestService busInfoRequestService;

    public void syncBus() {

        busInfoRequestService.requestBusInfo(1).subscribe(bus -> {
            int total = bus.getTotalCount();
            int numOfRows = bus.getNumOfRows();
            int maxPage = total / numOfRows;
            if (total % numOfRows > 0) maxPage++;

            Flux<BusItemsDto> busItems = Flux.range(1, maxPage).flatMap(pageNo -> {
                return busInfoRequestService.requestBusInfo(pageNo);
            });

            busItems.subscribe(items -> items.getBusItems().forEach(item -> {
                log.debug("item: {}", items.toString());
                addBus(item);
            }));
        });
    }

    public Optional<Bus> addBus(BusItemDto item) {
        Bus bus = Bus.builder()
                .routeId(item.getRouteId())
                .gradeNm(item.getGradeNm())
                .depPlandTime(item.getDepPlandTime())
                .arrPlandTime(item.getArrPlandTime())
                .depPlaceNm(item.getDepPlaceNm())
                .arrPlaceNm(item.getArrPlaceNm())
                .charge(item.getCharge())
                .build();

        return Optional.of(busRepository.save(bus));
    }

}
