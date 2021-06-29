package com.mhkim.tms.controller.v1.bus;

import com.mhkim.tms.controller.v1.bus.dto.BusDto;
import com.mhkim.tms.service.bus.BusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(tags = {"Bus"})
@RequestMapping("/api/v1/buses")
@RequiredArgsConstructor
@RestController
public class BusController {

    private static final String ALL_BUSES = "all-buses";

    private static final String GET_BUS = "get-bus";

    private static final String UPDATE_BUS = "update-bus";

    private static final String DELETE_BUS = "delete-bus";

    private final BusService busService;

    @ApiOperation(value = "버스 전체 조회")
    @GetMapping
    public ResponseEntity<CollectionModel<BusDto.Response>> getBuses() {
        return ResponseEntity.ok(
                CollectionModel.of(
                        busService.getBuses().stream()
                                .map(bus -> new BusDto.Response(bus)
                                        .add(linkTo(methodOn(BusController.class).getBus(bus.getBusIdx())).withSelfRel())
                                        .add(linkTo(BusController.class).slash(bus.getBusIdx()).withRel(UPDATE_BUS))
                                        .add(linkTo(methodOn(BusController.class).deleteBus(bus.getBusIdx())).withRel(DELETE_BUS))
                                )
                                .collect(toList())
                ).add(linkTo(methodOn(BusController.class).getBuses()).withSelfRel())
        );
    }

    @ApiOperation(value = "버스 개별 조회")
    @GetMapping(value = "/{busIdx}")
    public ResponseEntity<BusDto.Response> getBus(@PathVariable("busIdx") Long busIdx) {
        var bus = busService.getBus(busIdx);
        return ResponseEntity.ok(
                new BusDto.Response(bus)
                        .add(linkTo(methodOn(BusController.class).getBus(busIdx)).withSelfRel())
                        .add(linkTo(BusController.class).slash(busIdx).withRel(UPDATE_BUS))
                        .add(linkTo(methodOn(BusController.class).deleteBus(busIdx)).withRel(DELETE_BUS))
        );
    }

    @ApiOperation(value = "버스 추가")
    @PostMapping
    public ResponseEntity<BusDto.Response> addBus(@RequestBody BusDto.Request param) {
        var bus = busService.addBus(param.toEntity());
        return ResponseEntity.created(
                linkTo(methodOn(BusController.class).getBus(bus.getBusIdx())).withSelfRel().toUri()
        ).body(new BusDto.Response(bus).add(linkTo(methodOn(BusController.class).getBuses()).withRel(ALL_BUSES)));
    }

    @ApiOperation(value = "버스 삭제")
    @DeleteMapping(value = "/{busIdx}")
    public ResponseEntity<BusDto.Response> deleteBus(@PathVariable Long busIdx) {
        var bus = busService.deleteBus(busIdx);
        return ResponseEntity.ok(
                new BusDto.Response(bus)
                        .add(linkTo(methodOn(BusController.class).getBuses()).withSelfRel())
        );
    }

    @PostMapping(value = "/sync")
    public void syncBus() {
        busService.syncBus();
    }

}
