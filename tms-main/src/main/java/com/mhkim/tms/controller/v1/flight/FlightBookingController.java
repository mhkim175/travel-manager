package com.mhkim.tms.controller.v1.flight;

import com.mhkim.tms.controller.v1.flight.dto.FlightBookingDto;
import com.mhkim.tms.service.flight.FlightBookingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(tags = {"FlightBooking"})
@RequestMapping("/api/v1/flights/bookings")
@RequiredArgsConstructor
@RestController
public class FlightBookingController {

    private static final String ALL_FLIGHTBOOKINGS = "all-flightBookings";

    private static final String GET_FLIGHTBOOKING = "get-flightBooking";

    private static final String UPDATE_FLIGHTBOOKING = "update-flightBooking";

    private static final String DELETE_FLIGHTBOOKING = "delete-flightBooking";

    private final FlightBookingService flightBookingService;

    @ApiOperation(value = "항공편 예약 전체 조회")
    @GetMapping
    public ResponseEntity<CollectionModel<FlightBookingDto.Response>> getFlightBookings() {
        return ResponseEntity.ok(
                CollectionModel.of(
                        flightBookingService.getFlightBookings().stream()
                                .map(flightBooking -> new FlightBookingDto.Response(flightBooking)
                                        .add(linkTo(methodOn(FlightBookingController.class).getFlightBooking(flightBooking.getFlightBookIdx())).withSelfRel())
                                        .add(linkTo(FlightBookingController.class).slash(flightBooking.getFlightBookIdx()).withRel(UPDATE_FLIGHTBOOKING))
                                        .add(linkTo(methodOn(FlightBookingController.class).deleteFlightBooking(flightBooking.getFlightBookIdx())).withRel(DELETE_FLIGHTBOOKING))
                                )
                                .collect(toList())
                ).add(linkTo(methodOn(FlightBookingController.class).getFlightBookings()).withSelfRel())
        );
    }

    @ApiOperation(value = "예약자의 항공편 예약 목록 조회")
    @GetMapping(value = "/{userIdx}/user")
    public ResponseEntity<CollectionModel<FlightBookingDto.Response>> getFlightBookingList(@PathVariable Long userIdx) {
        return ResponseEntity.ok(
                CollectionModel.of(
                        flightBookingService.getFlightBookingsByUserId(userIdx).stream()
                                .map(flightBooking -> new FlightBookingDto.Response(flightBooking)
                                        .add(linkTo(methodOn(FlightBookingController.class).getFlightBooking(flightBooking.getFlightBookIdx())).withSelfRel())
                                        .add(linkTo(FlightBookingController.class).slash(flightBooking.getFlightBookIdx()).withRel(UPDATE_FLIGHTBOOKING))
                                        .add(linkTo(methodOn(FlightBookingController.class).deleteFlightBooking(flightBooking.getFlightBookIdx())).withRel(DELETE_FLIGHTBOOKING))
                                )
                                .collect(toList())
                ).add(linkTo(methodOn(FlightBookingController.class).getFlightBookings()).withSelfRel())
        );
    }

    @ApiOperation(value = "항공편 예약 개별 조회")
    @GetMapping(value = "/{flightBookingIdx}")
    public ResponseEntity<FlightBookingDto.Response> getFlightBooking(@PathVariable("flightBookingIdx") Long flightBookingIdx) {
        var flightBooking = flightBookingService.getFlightBooking(flightBookingIdx);
        return ResponseEntity.ok(
                new FlightBookingDto.Response(flightBooking)
                        .add(linkTo(methodOn(FlightBookingController.class).getFlightBooking(flightBookingIdx)).withSelfRel())
                        .add(linkTo(FlightBookingController.class).slash(flightBookingIdx).withRel(UPDATE_FLIGHTBOOKING))
                        .add(linkTo(methodOn(FlightBookingController.class).deleteFlightBooking(flightBookingIdx)).withRel(DELETE_FLIGHTBOOKING))
        );
    }

    @ApiOperation(value = "항공편 예약 추가")
    @PostMapping
    public ResponseEntity<FlightBookingDto.Response> addFlightBooking(@RequestBody FlightBookingDto.Request param) {
        var flightBooking = flightBookingService.addFlightBooking(param.toEntity());
        return ResponseEntity.created(
                linkTo(methodOn(FlightBookingController.class).getFlightBooking(flightBooking.getFlightBookIdx())).withSelfRel().toUri()
        ).body(new FlightBookingDto.Response(flightBooking).add(linkTo(methodOn(FlightBookingController.class).getFlightBookings()).withRel(ALL_FLIGHTBOOKINGS)));
    }

    @ApiOperation(value = "항공편 예약 삭제")
    @DeleteMapping(value = "/{flightBookingIdx}")
    public ResponseEntity<FlightBookingDto.Response> deleteFlightBooking(@PathVariable Long flightBookingIdx) {
        var flightBooking = flightBookingService.deleteFlightBooking(flightBookingIdx);
        return ResponseEntity.ok(
                new FlightBookingDto.Response(flightBooking)
                        .add(linkTo(methodOn(FlightBookingController.class).getFlightBookings()).withSelfRel())
        );
    }

}
