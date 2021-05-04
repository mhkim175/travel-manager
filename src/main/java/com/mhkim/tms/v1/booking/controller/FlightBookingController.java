package com.mhkim.tms.v1.booking.controller;

import com.mhkim.tms.advice.exception.CDataNotFoundException;
import com.mhkim.tms.v1.booking.controller.dto.FlightBookingDto;
import com.mhkim.tms.v1.booking.entity.FlightBooking;
import com.mhkim.tms.v1.booking.service.FlightBookingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Api(tags = {"4. Booking-Flight"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/booking")
public class FlightBookingController {

    private final FlightBookingService flightBookingService;

    @ApiOperation(value = "항공편 목록 조회")
    @GetMapping(value = "/flights")
    public ResponseEntity<List<FlightBookingDto.Response>> getFlightBookingList() {
        return ResponseEntity.ok(
                flightBookingService.getFlightBookingList().stream()
                        .map(FlightBookingDto.Response::new)
                        .collect(toList())
        );
    }

    @ApiOperation(value = "예약자의 항공편 목록 조회")
    @GetMapping(value = "/flight")
    public ResponseEntity<List<FlightBookingDto.Response>> getFlightBookingList(@RequestBody FlightBookingDto.Request param) {
        return ResponseEntity.ok(
                flightBookingService.getFlightBookingByUserId(param.getUserIdx()).stream()
                        .map(FlightBookingDto.Response::new)
                        .collect(toList())
        );
    }

    @ApiOperation(value = "항공편 조회")
    @GetMapping(value = "/flight/{flightBookIdx}")
    public ResponseEntity<FlightBookingDto.Response> getFlightBooking(@PathVariable("flightBookIdx") Long flightBookIdx) {
        return ResponseEntity.ok(
                flightBookingService.getFlightBooking(flightBookIdx)
                        .map(FlightBookingDto.Response::new)
                        .orElseThrow(() -> new CDataNotFoundException("FlightBooking not found"))
        );
    }

    @ApiOperation(value = "항공편 예약")
    @PostMapping(value = "/flight/book")
    public ResponseEntity<FlightBookingDto.Response> bookFlight(@RequestBody FlightBookingDto.Book param) {
        FlightBooking flightBooking = flightBookingService.bookFlight(param.getBookDate(), param.getFlightIdx(), param.getUserIdx());
        return new ResponseEntity<>(new FlightBookingDto.Response(flightBooking), HttpStatus.CREATED);
    }

    @ApiOperation(value = "항공편 삭제")
    @DeleteMapping(value = "/flight")
    public ResponseEntity<FlightBookingDto.Response> deleteQna(@RequestBody FlightBookingDto.Request param) {
        FlightBooking flightBooking = flightBookingService.deleteFlightBooking(param.getFlightBookIdx());
        return ResponseEntity.ok(new FlightBookingDto.Response(flightBooking));
    }

}
