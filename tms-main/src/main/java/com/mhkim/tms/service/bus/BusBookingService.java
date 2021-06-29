package com.mhkim.tms.service.bus;

import com.mhkim.tms.entity.bus.BusBooking;
import com.mhkim.tms.exception.error.AlreadyExistsException;
import com.mhkim.tms.exception.error.NotFoundException;
import com.mhkim.tms.repository.bus.BusBookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BusBookingService {

    private final BusBookingRepository busBookingRepository;

    public List<BusBooking> getBusBookings() {
        return busBookingRepository.findAll();
    }

    public List<BusBooking> getBusBookingsByUserId(Long userIdx) {
        return busBookingRepository.findAllByUserUserIdx(userIdx);
    }

    public BusBooking getBusBooking(Long busBookingIdx) {
        return busBookingRepository.findById(busBookingIdx)
                .orElseThrow(() -> new NotFoundException(BusBooking.class, busBookingIdx));
    }

    @Transactional
    public BusBooking addBusBooking(BusBooking busBookingRequest) {
        busBookingRepository.findByBusBusIdxAndUserUserIdxAndBookDate(busBookingRequest.getBus().getBusIdx(),
                busBookingRequest.getUser().getUserIdx(), busBookingRequest.getBookDate())
                .ifPresent(busBooking -> {
                    throw new AlreadyExistsException(BusBooking.class, busBookingRequest.getBus().getBusIdx(),
                            busBookingRequest.getUser().getUserIdx(), busBookingRequest.getBookDate());
                });

        return save(busBookingRequest);
    }

    @Transactional
    public BusBooking deleteBusBooking(Long busBookingIdx) {
        return busBookingRepository.findById(busBookingIdx)
                .map(busBooking -> {
                    busBookingRepository.deleteById(busBooking.getBusBookIdx());
                    return busBooking;
                })
                .orElseThrow(() -> new NotFoundException(BusBooking.class, busBookingIdx));
    }

    private BusBooking save(BusBooking busBooking) {
        return busBookingRepository.save(busBooking);
    }

}
