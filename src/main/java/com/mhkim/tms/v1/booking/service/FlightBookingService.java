package com.mhkim.tms.v1.booking.service;

import com.mhkim.tms.advice.exception.CDataNotFoundException;
import com.mhkim.tms.v1.booking.entity.FlightBooking;
import com.mhkim.tms.v1.booking.repository.FlightBookingRepository;
import com.mhkim.tms.v1.travelinfo.entity.Flight;
import com.mhkim.tms.v1.travelinfo.repository.FlightRepository;
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
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;

    public List<FlightBooking> getFlightBookingList() {
        return flightBookingRepository.findAll();
    }

    public Optional<FlightBooking> getFlightBooking(Long flightBookId) {
        return flightBookingRepository.findById(flightBookId);
    }

    public List<FlightBooking> getFlightBookingByUserId(Long userId) {
        return flightBookingRepository.findAllByUserUserId(userId);
    }

    @Transactional
    public FlightBooking bookFlight(LocalDate bookDate, Long flightId, Long userId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new CDataNotFoundException("Flight not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CDataNotFoundException("User not found"));

        return flightBookingRepository.save(
                FlightBooking.builder()
                        .bookDate(bookDate)
                        .flight(flight)
                        .user(user)
                        .build()
        );
    }

    @Transactional
    public FlightBooking deleteFlightBooking(Long flightBookId) {
        return getFlightBooking(flightBookId)
                .map(flightBooking -> {
                    flightBookingRepository.deleteById(flightBooking.getFlightBookId());
                    return flightBooking;
                }).orElseThrow(() -> new CDataNotFoundException("FlightBooking not found"));
    }

}
