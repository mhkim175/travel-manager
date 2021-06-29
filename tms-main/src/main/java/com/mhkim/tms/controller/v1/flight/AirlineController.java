package com.mhkim.tms.controller.v1.flight;

import com.mhkim.tms.controller.v1.flight.dto.AirlineDto;
import com.mhkim.tms.service.flight.AirlineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(tags = {"Airline"})
@RequestMapping("/api/v1/flights/airlines")
@RequiredArgsConstructor
@RestController
public class AirlineController {

    private static final String ALL_AIRLINES = "all-airlines";

    private static final String GET_AIRLINE = "get-airline";

    private static final String UPDATE_AIRLINE = "update-airline";

    private static final String DELETE_AIRLINE = "delete-airline";

    private final AirlineService airlineService;

    @ApiOperation(value = "항공사 전체 조회")
    @GetMapping
    public ResponseEntity<CollectionModel<AirlineDto.Response>> getAirlines() {
        return ResponseEntity.ok(
                CollectionModel.of(
                        airlineService.getAirlines().stream()
                                .map(airline -> new AirlineDto.Response(airline)
                                        .add(linkTo(methodOn(AirlineController.class).getAirline(airline.getAirlineIdx())).withSelfRel())
                                        .add(linkTo(AirlineController.class).slash(airline.getAirlineIdx()).withRel(UPDATE_AIRLINE))
                                        .add(linkTo(methodOn(AirlineController.class).deleteAirline(airline.getAirlineIdx())).withRel(DELETE_AIRLINE))
                                )
                                .collect(toList())
                ).add(linkTo(methodOn(AirlineController.class).getAirlines()).withSelfRel())
        );
    }

    @ApiOperation(value = "항공사 개별 조회")
    @GetMapping(value = "/{airlineIdx}")
    public ResponseEntity<AirlineDto.Response> getAirline(@PathVariable("airlineIdx") Long airlineIdx) {
        var airline = airlineService.getAirline(airlineIdx);
        return ResponseEntity.ok(
                new AirlineDto.Response(airline)
                        .add(linkTo(methodOn(AirlineController.class).getAirline(airlineIdx)).withSelfRel())
                        .add(linkTo(AirlineController.class).slash(airlineIdx).withRel(UPDATE_AIRLINE))
                        .add(linkTo(methodOn(AirlineController.class).deleteAirline(airlineIdx)).withRel(DELETE_AIRLINE))
        );
    }

    @ApiOperation(value = "항공사 추가")
    @PostMapping
    public ResponseEntity<AirlineDto.Response> addAirline(@RequestBody AirlineDto.Request param) {
        var airline = airlineService.addAirline(param.toEntity());
        return ResponseEntity.created(
                linkTo(methodOn(AirlineController.class).getAirline(airline.getAirlineIdx())).withSelfRel().toUri()
        ).body(new AirlineDto.Response(airline).add(linkTo(methodOn(AirlineController.class).getAirlines()).withRel(ALL_AIRLINES)));
    }

    @ApiOperation(value = "항공사 삭제")
    @DeleteMapping(value = "/{airlineIdx}")
    public ResponseEntity<AirlineDto.Response> deleteAirline(@PathVariable Long airlineIdx) {
        var airline = airlineService.deleteAirline(airlineIdx);
        return ResponseEntity.ok(
                new AirlineDto.Response(airline)
                        .add(linkTo(methodOn(AirlineController.class).getAirlines()).withSelfRel())
        );
    }

}
