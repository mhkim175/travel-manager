package com.mhkim.tms.service.ship;

import com.mhkim.tms.entity.ship.ShipBooking;
import com.mhkim.tms.exception.error.AlreadyExistsException;
import com.mhkim.tms.exception.error.NotFoundException;
import com.mhkim.tms.repository.ship.ShipBookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShipBookingService {

    private final ShipBookingRepository shipBookingRepository;

    public List<ShipBooking> getShipBookings() {
        return shipBookingRepository.findAll();
    }

    public List<ShipBooking> getShipBookingsByUserId(Long userIdx) {
        return shipBookingRepository.findAllByUserUserIdx(userIdx);
    }

    public ShipBooking getShipBooking(Long shipBookingIdx) {
        return shipBookingRepository.findById(shipBookingIdx)
                .orElseThrow(() -> new NotFoundException(ShipBooking.class, shipBookingIdx));
    }

    @Transactional
    public ShipBooking addShipBooking(ShipBooking shipBookingRequest) {
        shipBookingRepository.findByShipShipIdxAndUserUserIdxAndBookDate(shipBookingRequest.getShip().getShipIdx(),
                shipBookingRequest.getUser().getUserIdx(), shipBookingRequest.getBookDate())
                .ifPresent(shipBooking -> {
                    throw new AlreadyExistsException(ShipBooking.class, shipBookingRequest.getShip().getShipIdx(),
                            shipBookingRequest.getUser().getUserIdx(), shipBookingRequest.getBookDate());
                });

        return save(shipBookingRequest);
    }

    @Transactional
    public ShipBooking deleteShipBooking(Long shipBookingIdx) {
        return shipBookingRepository.findById(shipBookingIdx)
                .map(shipBooking -> {
                    shipBookingRepository.deleteById(shipBooking.getShipBookIdx());
                    return shipBooking;
                })
                .orElseThrow(() -> new NotFoundException(ShipBooking.class, shipBookingIdx));
    }

    private ShipBooking save(ShipBooking shipBooking) {
        return shipBookingRepository.save(shipBooking);
    }

}
