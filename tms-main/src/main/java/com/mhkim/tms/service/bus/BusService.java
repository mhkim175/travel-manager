package com.mhkim.tms.service.bus;

import com.mhkim.tms.controller.v1.bus.dto.BusItemsDto;
import com.mhkim.tms.entity.bus.Bus;
import com.mhkim.tms.exception.error.AlreadyExistsException;
import com.mhkim.tms.exception.error.NotFoundException;
import com.mhkim.tms.repository.bus.BusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BusService {

    private final BusRepository busRepository;
    private final BusInfoRequestService busInfoRequestService;

    public List<Bus> getBuses() {
        return busRepository.findAll();
    }

    public Bus getBus(Long busIdx) {
        return busRepository.findById(busIdx)
                .orElseThrow(() -> new NotFoundException(Bus.class, busIdx));
    }

    @Transactional
    public Bus addBus(Bus busRequest) {
        busRepository.findByRouteId(busRequest.getRouteId())
                .ifPresent(bus -> {
                    throw new AlreadyExistsException(Bus.class, busRequest.getRouteId());
                });

        return save(busRequest);
    }

    @Transactional
    public Bus deleteBus(Long busIdx) {
        return busRepository.findById(busIdx)
                .map(bus -> {
                    busRepository.deleteById(bus.getBusIdx());
                    return bus;
                })
                .orElseThrow(() -> new NotFoundException(Bus.class, busIdx));
    }

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
                save(item.toEntity());
            }));
        });
    }

    private Bus save(Bus bus) {
        return busRepository.save(bus);
    }

}
