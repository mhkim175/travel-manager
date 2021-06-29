package com.mhkim.tms.controller.v1.flight;

import com.mhkim.tms.controller.v1.flight.dto.AirportDto;
import com.mhkim.tms.service.flight.AirportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(tags = {"Airport"})
@RequestMapping("/api/v1/flights/airports")
@RequiredArgsConstructor
@RestController
public class AirportController {

    private static final String ALL_AIRPORTS = "all-airports";

    private static final String GET_AIRPORT = "get-airport";

    private static final String UPDATE_AIRPORT = "update-airport";

    private static final String DELETE_AIRPORT = "delete-airport";

    private final AirportService airportService;

    @ApiOperation(value = "공항 전체 조회")
    @GetMapping
    public ResponseEntity<CollectionModel<AirportDto.Response>> getAirports() {
        return ResponseEntity.ok(
                CollectionModel.of(
                        airportService.getAirports().stream()
                                .map(airport -> new AirportDto.Response(airport)
                                        .add(linkTo(methodOn(AirportController.class).getAirport(airport.getAirportIdx())).withSelfRel())
                                        .add(linkTo(AirportController.class).slash(airport.getAirportIdx()).withRel(UPDATE_AIRPORT))
                                        .add(linkTo(methodOn(AirportController.class).deleteAirport(airport.getAirportIdx())).withRel(DELETE_AIRPORT))
                                )
                                .collect(toList())
                ).add(linkTo(methodOn(AirportController.class).getAirports()).withSelfRel())
        );
    }

    @ApiOperation(value = "공항 개별 조회")
    @GetMapping(value = "/{airportIdx}")
    public ResponseEntity<AirportDto.Response> getAirport(@PathVariable("airportIdx") Long airportIdx) {
        var airport = airportService.getAirport(airportIdx);
        return ResponseEntity.ok(
                new AirportDto.Response(airport)
                        .add(linkTo(methodOn(AirportController.class).getAirport(airportIdx)).withSelfRel())
                        .add(linkTo(AirportController.class).slash(airportIdx).withRel(UPDATE_AIRPORT))
                        .add(linkTo(methodOn(AirportController.class).deleteAirport(airportIdx)).withRel(DELETE_AIRPORT))
        );
    }

    @ApiOperation(value = "공항 추가")
    @PostMapping
    public ResponseEntity<AirportDto.Response> addAirport(@RequestBody AirportDto.Request param) {
        var airport = airportService.addAirport(param.toEntity());
        return ResponseEntity.created(
                linkTo(methodOn(AirportController.class).getAirport(airport.getAirportIdx())).withSelfRel().toUri()
        ).body(new AirportDto.Response(airport).add(linkTo(methodOn(AirportController.class).getAirports()).withRel(ALL_AIRPORTS)));
    }

    @ApiOperation(value = "공항 삭제")
    @DeleteMapping(value = "/{airportIdx}")
    public ResponseEntity<AirportDto.Response> deleteAirport(@PathVariable Long airportIdx) {
        var airport = airportService.deleteAirport(airportIdx);
        return ResponseEntity.ok(
                new AirportDto.Response(airport)
                        .add(linkTo(methodOn(AirportController.class).getAirports()).withSelfRel())
        );
    }

}
