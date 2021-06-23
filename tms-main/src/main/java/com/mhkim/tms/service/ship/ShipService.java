package com.mhkim.tms.service.ship;

import com.mhkim.tms.controller.v1.ship.dto.ShipItemDto;
import com.mhkim.tms.controller.v1.ship.dto.ShipItemsDto;
import com.mhkim.tms.entity.ship.Ship;
import com.mhkim.tms.repository.ship.ShipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShipService {

    private final ShipRepository shipRepository;
    private final ShipInfoRequestService shipInfoRequestService;

    public void syncShip() {

        shipInfoRequestService.requestShipInfo(1).subscribe(ship -> {
            int total = ship.getTotalCount();
            int numOfRows = ship.getNumOfRows();
            int maxPage = total / numOfRows;
            if (total % numOfRows > 0) maxPage++;

            Flux<ShipItemsDto> shipItems = Flux.range(1, maxPage).flatMap(pageNo -> {
                return shipInfoRequestService.requestShipInfo(pageNo);
            });

            shipItems.subscribe(items -> items.getShipItems().forEach(item -> {
                log.debug("item: {}", items.toString());
                addShip(item);
            }));
        });
    }

    public Optional<Ship> addShip(ShipItemDto item) {
        Ship ship = Ship.builder()
                .vihicleNm(item.getVihicleNm())
                .depPlaceNm(item.getDepPlaceNm())
                .arrPlaceNm(item.getArrPlaceNm())
                .depPlandTime(item.getDepPlandTime())
                .arrPlandTime(item.getArrPlandTime())
                .charge(item.getCharge())
                .build();

        return Optional.of(shipRepository.save(ship));
    }

}
