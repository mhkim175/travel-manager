package com.mhkim.tms.v1.booking.service;

import com.mhkim.tms.advice.exception.CDataNotFoundException;
import com.mhkim.tms.v1.booking.entity.FlightBooking;
import com.mhkim.tms.v1.booking.repository.FlightBookingRepository;
import com.mhkim.tms.v1.travelinfo.entity.flight.FlightSeat;
import com.mhkim.tms.v1.travelinfo.repository.flight.FlightRepository;
import com.mhkim.tms.v1.travelinfo.repository.flight.FlightSeatRepository;
import com.mhkim.tms.v1.user.entity.User;
import com.mhkim.tms.v1.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FlightBookingService {

    private final FlightBookingRepository flightBookingRepository;
    private final FlightSeatRepository flightSeatRepository;
    private final UserRepository userRepository;

    public List<FlightBooking> getFlightBookingList() {
        return flightBookingRepository.findAll();
    }

    public Optional<FlightBooking> getFlightBooking(Long flightBookIdx) {
        return flightBookingRepository.findById(flightBookIdx);
    }

    public List<FlightBooking> getFlightBookingByUserId(Long userIdx) {
        return flightBookingRepository.findAllByUserUserIdx(userIdx);
    }

    @Transactional
    public FlightBooking bookFlight(LocalDate bookDate, Long flightSeatIdx, Long userIdx) {
        FlightSeat flightSeat = flightSeatRepository.findById(flightSeatIdx)
                .orElseThrow(() -> new CDataNotFoundException("FlightSeat not found"));
        User user = userRepository.findById(userIdx)
                .orElseThrow(() -> new CDataNotFoundException("User not found"));

        return flightBookingRepository.save(
                FlightBooking.builder()
                        .bookDate(bookDate)
                        .flightSeat(flightSeat)
                        .user(user)
                        .build()
        );
    }

    @Transactional
    public FlightBooking deleteFlightBooking(Long flightBookIdx) {
        return getFlightBooking(flightBookIdx)
                .map(flightBooking -> {
                    flightBookingRepository.deleteById(flightBooking.getFlightBookIdx());
                    return flightBooking;
                }).orElseThrow(() -> new CDataNotFoundException("FlightBooking not found"));
    }

}
