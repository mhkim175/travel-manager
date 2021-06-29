package com.mhkim.tms.controller.v1.flight;

import com.mhkim.tms.controller.v1.flight.dto.FlightBookingDto;
import com.mhkim.tms.entity.flight.FlightBooking;
import com.mhkim.tms.service.flight.FlightBookingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Api(tags = {"Booking-Flight"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/flights/bookings")
public class FlightBookingController {

    private final FlightBookingService flightBookingService;

    @ApiOperation(value = "예약 항공편 전체 조회")
    public ResponseEntity<List<FlightBookingDto.Response>> getFlightBookingList() {
        return ResponseEntity.ok(
                flightBookingService.getFlightBookings().stream()
                        .map(FlightBookingDto.Response::new)
                        .collect(toList())
        );
    }

    @ApiOperation(value = "예약자의 항공편 목록 조회")
    @GetMapping(value = "/{userIdx}/user")
    public ResponseEntity<List<FlightBookingDto.Response>> getFlightBookingList(@PathVariable Long userIdx) {
        return ResponseEntity.ok(
                flightBookingService.getFlightBookingsByUserId(userIdx).stream()
                        .map(FlightBookingDto.Response::new)
                        .collect(toList())
        );
    }

    @ApiOperation(value = "예약 항공편 개별 조회")
    @GetMapping(value = "/{flightBookIdx}")
    public ResponseEntity<FlightBookingDto.Response> getFlightBooking(@PathVariable("flightBookIdx") Long flightBookIdx) {
        return ResponseEntity.ok(
                new FlightBookingDto.Response(
                        flightBookingService.getFlightBooking(flightBookIdx)
                )
        );
    }

    @ApiOperation(value = "항공편 예약")
    @PostMapping
    public ResponseEntity<FlightBookingDto.Response> bookFlight(@RequestBody FlightBookingDto.Request param) {
        FlightBooking flightBooking = flightBookingService.addFlightBooking(param.toEntity());
        return new ResponseEntity<>(new FlightBookingDto.Response(flightBooking), HttpStatus.CREATED);
    }

    @ApiOperation(value = "예약 항공편 삭제")
    @DeleteMapping(value = "/{flightBookIdx}")
    public ResponseEntity<FlightBookingDto.Response> deleteQna(@PathVariable("flightBookIdx") Long flightBookIdx) {
        FlightBooking flightBooking = flightBookingService.deleteFlightBooking(flightBookIdx);
        return ResponseEntity.ok(new FlightBookingDto.Response(flightBooking));
    }

}
