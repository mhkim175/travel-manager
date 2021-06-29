package com.mhkim.tms.controller.v1.flight;

import com.mhkim.tms.controller.v1.flight.dto.FlightDto;
import com.mhkim.tms.service.flight.FlightService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(tags = {"Flight"})
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
@RestController
public class FlightController {

    private static final String ALL_FLIGHTS = "all-flights";

    private static final String GET_FLIGHT = "get-flight";

    private static final String UPDATE_FLIGHT = "update-flight";

    private static final String DELETE_FLIGHT = "delete-flight";

    private final FlightService flightService;

    @ApiOperation(value = "항공편 전체 조회")
    @GetMapping
    public ResponseEntity<CollectionModel<FlightDto.Response>> getFlights() {
        return ResponseEntity.ok(
                CollectionModel.of(
                        flightService.getFlights().stream()
                                .map(flight -> new FlightDto.Response(flight)
                                        .add(linkTo(methodOn(FlightController.class).getFlight(flight.getFlightIdx())).withSelfRel())
                                        .add(linkTo(FlightController.class).slash(flight.getFlightIdx()).withRel(UPDATE_FLIGHT))
                                        .add(linkTo(methodOn(FlightController.class).deleteFlight(flight.getFlightIdx())).withRel(DELETE_FLIGHT))
                                )
                                .collect(toList())
                ).add(linkTo(methodOn(FlightController.class).getFlights()).withSelfRel())
        );
    }

    @ApiOperation(value = "항공편 개별 조회")
    @GetMapping(value = "/{flightIdx}")
    public ResponseEntity<FlightDto.Response> getFlight(@PathVariable("flightIdx") Long flightIdx) {
        var flight = flightService.getFlight(flightIdx);
        return ResponseEntity.ok(
                new FlightDto.Response(flight)
                        .add(linkTo(methodOn(FlightController.class).getFlight(flightIdx)).withSelfRel())
                        .add(linkTo(FlightController.class).slash(flightIdx).withRel(UPDATE_FLIGHT))
                        .add(linkTo(methodOn(FlightController.class).deleteFlight(flightIdx)).withRel(DELETE_FLIGHT))
        );
    }

    @ApiOperation(value = "항공편 추가")
    @PostMapping
    public ResponseEntity<FlightDto.Response> addFlight(@RequestBody FlightDto.Request param) {
        var flight = flightService.addFlight(param.toEntity());
        return ResponseEntity.created(
                linkTo(methodOn(FlightController.class).getFlight(flight.getFlightIdx())).withSelfRel().toUri()
        ).body(new FlightDto.Response(flight).add(linkTo(methodOn(FlightController.class).getFlights()).withRel(ALL_FLIGHTS)));
    }

    @ApiOperation(value = "항공편 삭제")
    @DeleteMapping(value = "/{flightIdx}")
    public ResponseEntity<FlightDto.Response> deleteFlight(@PathVariable Long flightIdx) {
        var flight = flightService.deleteFlight(flightIdx);
        return ResponseEntity.ok(
                new FlightDto.Response(flight)
                        .add(linkTo(methodOn(FlightController.class).getFlights()).withSelfRel())
        );
    }
    
    @PostMapping(value = "/sync")
    public void syncFlight() {
        flightService.syncFlight();
    }

}
