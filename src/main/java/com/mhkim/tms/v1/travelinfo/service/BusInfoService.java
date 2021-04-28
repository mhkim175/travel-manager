package com.mhkim.tms.v1.travelinfo.service;

import com.mhkim.tms.v1.travelinfo.controller.dto.BusInfoItemDto;
import com.mhkim.tms.v1.travelinfo.controller.dto.BusInfoItemsDto;
import com.mhkim.tms.v1.travelinfo.entity.BusInfo;
import com.mhkim.tms.v1.travelinfo.repository.BusInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BusInfoService {

    private final BusInfoRepository busInfoRepository;
    private final BusInfoRequestService busInfoRequestService;

    public void syncBusInfo() {

        busInfoRequestService.requestBusInfo(1).subscribe(busInfo -> {
            int total = busInfo.getTotalCount();
            int numOfRows = busInfo.getNumOfRows();
            int maxPage = total / numOfRows;
            if (total % numOfRows > 0) maxPage++;

            Flux<BusInfoItemsDto> busInfoItems = Flux.range(1, maxPage).flatMap(pageNo -> {
                return busInfoRequestService.requestBusInfo(pageNo);
            });

            busInfoItems.subscribe(items -> items.getBusInfoItems().forEach(item -> {
                log.debug("item: {}", items.toString());
                addBusInfo(item);
            }));
        });
    }

    public Optional<BusInfo> addBusInfo(BusInfoItemDto item) {
        BusInfo busInfo = BusInfo.builder()
                .routeId(item.getRouteId())
                .gradeNm(item.getGradeNm())
                .depPlandTime(item.getDepPlandTime())
                .arrPlandTime(item.getArrPlandTime())
                .depPlaceNm(item.getDepPlaceNm())
                .arrPlaceNm(item.getArrPlaceNm())
                .charge(item.getCharge())
                .build();

        return Optional.of(busInfoRepository.save(busInfo));
    }

}
