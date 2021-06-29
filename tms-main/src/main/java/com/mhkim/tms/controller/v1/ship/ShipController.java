package com.mhkim.tms.controller.v1.ship;

import com.mhkim.tms.controller.v1.ship.dto.ShipDto;
import com.mhkim.tms.service.ship.ShipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(tags = {"Ship"})
@RequestMapping("/api/v1/ships")
@RequiredArgsConstructor
@RestController
public class ShipController {
    
    private static final String ALL_SHIPS = "all-ships";

    private static final String GET_SHIP = "get-ship";

    private static final String UPDATE_SHIP = "update-ship";

    private static final String DELETE_SHIP = "delete-ship";

    private final ShipService shipService;

    @ApiOperation(value = "선박 전체 조회")
    @GetMapping
    public ResponseEntity<CollectionModel<ShipDto.Response>> getShips() {
        return ResponseEntity.ok(
                CollectionModel.of(
                        shipService.getShips().stream()
                                .map(ship -> new ShipDto.Response(ship)
                                        .add(linkTo(methodOn(ShipController.class).getShip(ship.getShipIdx())).withSelfRel())
                                        .add(linkTo(ShipController.class).slash(ship.getShipIdx()).withRel(UPDATE_SHIP))
                                        .add(linkTo(methodOn(ShipController.class).deleteShip(ship.getShipIdx())).withRel(DELETE_SHIP))
                                )
                                .collect(toList())
                ).add(linkTo(methodOn(ShipController.class).getShips()).withSelfRel())
        );
    }

    @ApiOperation(value = "선박 개별 조회")
    @GetMapping(value = "/{shipIdx}")
    public ResponseEntity<ShipDto.Response> getShip(@PathVariable("shipIdx") Long shipIdx) {
        var ship = shipService.getShip(shipIdx);
        return ResponseEntity.ok(
                new ShipDto.Response(ship)
                        .add(linkTo(methodOn(ShipController.class).getShip(shipIdx)).withSelfRel())
                        .add(linkTo(ShipController.class).slash(shipIdx).withRel(UPDATE_SHIP))
                        .add(linkTo(methodOn(ShipController.class).deleteShip(shipIdx)).withRel(DELETE_SHIP))
        );
    }

    @ApiOperation(value = "선박 추가")
    @PostMapping
    public ResponseEntity<ShipDto.Response> addShip(@RequestBody ShipDto.Request param) {
        var ship = shipService.addShip(param.toEntity());
        return ResponseEntity.created(
                linkTo(methodOn(ShipController.class).getShip(ship.getShipIdx())).withSelfRel().toUri()
        ).body(new ShipDto.Response(ship).add(linkTo(methodOn(ShipController.class).getShips()).withRel(ALL_SHIPS)));
    }

    @ApiOperation(value = "선박 삭제")
    @DeleteMapping(value = "/{shipIdx}")
    public ResponseEntity<ShipDto.Response> deleteShip(@PathVariable Long shipIdx) {
        var ship = shipService.deleteShip(shipIdx);
        return ResponseEntity.ok(
                new ShipDto.Response(ship)
                        .add(linkTo(methodOn(ShipController.class).getShips()).withSelfRel())
        );
    }
    
    @PostMapping(value = "/sync")
    public void syncShip() {
        shipService.syncShip();
    }

}
