package com.mhkim.tms.v1.travelinfo.service;

import com.mhkim.tms.v1.travelinfo.controller.dto.ShipInfoItemDto;
import com.mhkim.tms.v1.travelinfo.controller.dto.ShipInfoItemsDto;
import com.mhkim.tms.v1.travelinfo.entity.ShipInfo;
import com.mhkim.tms.v1.travelinfo.repository.ShipInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShipInfoService {

    private final ShipInfoRepository shipInfoRepository;
    private final ShipInfoRequestService shipInfoRequestService;

    public void syncShipInfo() {

        shipInfoRequestService.requestShipInfo(1).subscribe(shipInfo -> {
            log.debug("shipInfo: {}", shipInfo);

            int total = shipInfo.getTotalCount();
            int numOfRows = shipInfo.getNumOfRows();
            int maxPage = total / numOfRows;
            if (total % numOfRows > 0)
                maxPage++;

            Flux<ShipInfoItemsDto> shipInfoItems = Flux.range(1, maxPage).flatMap(pageNo -> {
                log.debug("pageNo: {}", pageNo);
                return shipInfoRequestService.requestShipInfo(pageNo);
            });

            shipInfoItems.subscribe(items -> items.getShipInfoItems().forEach(item -> {
                log.debug("item: {}", items.toString());
                addShipInfo(item);
            }));
        });
    }

    public Optional<ShipInfo> addShipInfo(ShipInfoItemDto item) {
        ShipInfo shipInfo = ShipInfo.builder()
                .vihicleNm(item.getVihicleNm())
                .depPlaceNm(item.getDepPlaceNm())
                .arrPlaceNm(item.getArrPlaceNm())
                .depPlandTime(item.getDepPlandTime())
                .arrPlandTime(item.getArrPlandTime())
                .charge(item.getCharge())
                .build();

        return Optional.of(shipInfoRepository.save(shipInfo));
    }

}
