package com.mhkim.tms.controller.v1.flight;

import com.mhkim.tms.controller.v1.flight.dto.FlightSeatDto;
import com.mhkim.tms.service.flight.FlightSeatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(tags = {"FlightSeat"})
@RequestMapping("/api/v1/flights/seats")
@RequiredArgsConstructor
@RestController
public class FlightSeatController {

    private static final String ALL_FLIGHTSEATS = "all-flightSeats";

    private static final String GET_FLIGHTSEAT = "get-flightSeat";

    private static final String UPDATE_FLIGHTSEAT = "update-flightSeat";

    private static final String DELETE_FLIGHTSEAT = "delete-flightSeat";

    private final FlightSeatService flightSeatService;

    @ApiOperation(value = "공항 전체 조회")
    @GetMapping
    public ResponseEntity<CollectionModel<FlightSeatDto.Response>> getFlightSeats() {
        return ResponseEntity.ok(
                CollectionModel.of(
                        flightSeatService.getFlightSeats().stream()
                                .map(flightSeat -> new FlightSeatDto.Response(flightSeat)
                                        .add(linkTo(methodOn(FlightSeatController.class).getFlightSeat(flightSeat.getFlightSeatIdx())).withSelfRel())
                                        .add(linkTo(FlightSeatController.class).slash(flightSeat.getFlightSeatIdx()).withRel(UPDATE_FLIGHTSEAT))
                                        .add(linkTo(methodOn(FlightSeatController.class).deleteFlightSeat(flightSeat.getFlightSeatIdx())).withRel(DELETE_FLIGHTSEAT))
                                )
                                .collect(toList())
                ).add(linkTo(methodOn(FlightSeatController.class).getFlightSeats()).withSelfRel())
        );
    }

    @ApiOperation(value = "공항 개별 조회")
    @GetMapping(value = "/{flightSeatIdx}")
    public ResponseEntity<FlightSeatDto.Response> getFlightSeat(@PathVariable("flightSeatIdx") Long flightSeatIdx) {
        var flightSeat = flightSeatService.getFlightSeat(flightSeatIdx);
        return ResponseEntity.ok(
                new FlightSeatDto.Response(flightSeat)
                        .add(linkTo(methodOn(FlightSeatController.class).getFlightSeat(flightSeatIdx)).withSelfRel())
                        .add(linkTo(FlightSeatController.class).slash(flightSeatIdx).withRel(UPDATE_FLIGHTSEAT))
                        .add(linkTo(methodOn(FlightSeatController.class).deleteFlightSeat(flightSeatIdx)).withRel(DELETE_FLIGHTSEAT))
        );
    }

    @ApiOperation(value = "공항 추가")
    @PostMapping
    public ResponseEntity<FlightSeatDto.Response> addFlightSeat(@RequestBody FlightSeatDto.Request param) {
        var flightSeat = flightSeatService.addFlightSeat(param.toEntity());
        return ResponseEntity.created(
                linkTo(methodOn(FlightSeatController.class).getFlightSeat(flightSeat.getFlightSeatIdx())).withSelfRel().toUri()
        ).body(new FlightSeatDto.Response(flightSeat).add(linkTo(methodOn(FlightSeatController.class).getFlightSeats()).withRel(ALL_FLIGHTSEATS)));
    }

    @ApiOperation(value = "공항 삭제")
    @DeleteMapping(value = "/{flightSeatIdx}")
    public ResponseEntity<FlightSeatDto.Response> deleteFlightSeat(@PathVariable Long flightSeatIdx) {
        var flightSeat = flightSeatService.deleteFlightSeat(flightSeatIdx);
        return ResponseEntity.ok(
                new FlightSeatDto.Response(flightSeat)
                        .add(linkTo(methodOn(FlightSeatController.class).getFlightSeats()).withSelfRel())
        );
    }

}
