package com.mhkim.tms.service.flight;

import com.mhkim.tms.entity.flight.FlightBooking;
import com.mhkim.tms.exception.error.AlreadyExistsException;
import com.mhkim.tms.exception.error.NotFoundException;
import com.mhkim.tms.repository.flight.FlightBookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FlightBookingService {

    private final FlightBookingRepository flightBookingRepository;

    public List<FlightBooking> getFlightBookings() {
        return flightBookingRepository.findAll();
    }

    public List<FlightBooking> getFlightBookingsByUserId(Long userIdx) {
        return flightBookingRepository.findAllByUserUserIdx(userIdx);
    }

    public FlightBooking getFlightBooking(Long flightBookingIdx) {
        return flightBookingRepository.findById(flightBookingIdx)
                .orElseThrow(() -> new NotFoundException(FlightBooking.class, flightBookingIdx));
    }

    @Transactional
    public FlightBooking addFlightBooking(FlightBooking flightBookingRequest) {
        flightBookingRepository.findByFlightSeatFlightSeatIdxAndUserUserIdxAndBookDate(flightBookingRequest.getFlightSeat().getFlightSeatIdx(),
                flightBookingRequest.getUser().getUserIdx(), flightBookingRequest.getBookDate())
                .ifPresent(flightBooking -> {
                    throw new AlreadyExistsException(FlightBooking.class, flightBookingRequest.getFlightSeat().getFlightSeatIdx(),
                            flightBookingRequest.getUser().getUserIdx(), flightBookingRequest.getBookDate());
                });

        return save(flightBookingRequest);
    }

    @Transactional
    public FlightBooking deleteFlightBooking(Long flightBookingIdx) {
        return flightBookingRepository.findById(flightBookingIdx)
                .map(flightBooking -> {
                    flightBookingRepository.deleteById(flightBooking.getFlightBookIdx());
                    return flightBooking;
                })
                .orElseThrow(() -> new NotFoundException(FlightBooking.class, flightBookingIdx));
    }

    private FlightBooking save(FlightBooking flightBooking) {
        return flightBookingRepository.save(flightBooking);
    }

}
