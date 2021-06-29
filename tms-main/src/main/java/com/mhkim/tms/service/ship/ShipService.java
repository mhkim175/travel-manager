package com.mhkim.tms.service.ship;

import com.mhkim.tms.controller.v1.ship.dto.ShipItemsDto;
import com.mhkim.tms.entity.ship.Ship;
import com.mhkim.tms.exception.error.AlreadyExistsException;
import com.mhkim.tms.exception.error.NotFoundException;
import com.mhkim.tms.repository.ship.ShipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShipService {

    private final ShipRepository shipRepository;
    private final ShipInfoRequestService shipInfoRequestService;

    public List<Ship> getShips() {
        return shipRepository.findAll();
    }

    public Ship getShip(Long shipIdx) {
        return shipRepository.findById(shipIdx)
                .orElseThrow(() -> new NotFoundException(Ship.class, shipIdx));
    }

    @Transactional
    public Ship addShip(Ship shipRequest) {
        shipRepository.findByVihicleNm(shipRequest.getVihicleNm())
                .ifPresent(ship -> {
                    throw new AlreadyExistsException(Ship.class, shipRequest.getVihicleNm());
                });

        return save(shipRequest);
    }

    @Transactional
    public Ship deleteShip(Long shipIdx) {
        return shipRepository.findById(shipIdx)
                .map(ship -> {
                    shipRepository.deleteById(ship.getShipIdx());
                    return ship;
                })
                .orElseThrow(() -> new NotFoundException(Ship.class, shipIdx));
    }
    
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
                save(item.toEntity());
            }));
        });
    }

    private Ship save(Ship ship) {
        return shipRepository.save(ship);
    }

}
