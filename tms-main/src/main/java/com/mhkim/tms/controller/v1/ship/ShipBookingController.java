package com.mhkim.tms.controller.v1.ship;

import com.mhkim.tms.controller.v1.ship.dto.ShipBookingDto;
import com.mhkim.tms.service.ship.ShipBookingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(tags = {"ShipBooking"})
@RequestMapping("/api/v1/ships/bookings")
@RequiredArgsConstructor
@RestController
public class ShipBookingController {

    private static final String ALL_SHIPBOOKINGS = "all-shipBookings";

    private static final String GET_SHIPBOOKING = "get-shipBooking";

    private static final String UPDATE_SHIPBOOKING = "update-shipBooking";

    private static final String DELETE_SHIPBOOKING = "delete-shipBooking";

    private final ShipBookingService shipBookingService;

    @ApiOperation(value = "선박 예약 전체 조회")
    @GetMapping
    public ResponseEntity<CollectionModel<ShipBookingDto.Response>> getShipBookings() {
        return ResponseEntity.ok(
                CollectionModel.of(
                        shipBookingService.getShipBookings().stream()
                                .map(shipBooking -> new ShipBookingDto.Response(shipBooking)
                                        .add(linkTo(methodOn(ShipBookingController.class).getShipBooking(shipBooking.getShipBookIdx())).withSelfRel())
                                        .add(linkTo(ShipBookingController.class).slash(shipBooking.getShipBookIdx()).withRel(UPDATE_SHIPBOOKING))
                                        .add(linkTo(methodOn(ShipBookingController.class).deleteShipBooking(shipBooking.getShipBookIdx())).withRel(DELETE_SHIPBOOKING))
                                )
                                .collect(toList())
                ).add(linkTo(methodOn(ShipBookingController.class).getShipBookings()).withSelfRel())
        );
    }

    @ApiOperation(value = "예약자의 선박 예약 목록 조회")
    @GetMapping(value = "/{userIdx}/user")
    public ResponseEntity<CollectionModel<ShipBookingDto.Response>> getShipBookingList(@PathVariable Long userIdx) {
        return ResponseEntity.ok(
                CollectionModel.of(
                        shipBookingService.getShipBookingsByUserId(userIdx).stream()
                                .map(shipBooking -> new ShipBookingDto.Response(shipBooking)
                                        .add(linkTo(methodOn(ShipBookingController.class).getShipBooking(shipBooking.getShipBookIdx())).withSelfRel())
                                        .add(linkTo(ShipBookingController.class).slash(shipBooking.getShipBookIdx()).withRel(UPDATE_SHIPBOOKING))
                                        .add(linkTo(methodOn(ShipBookingController.class).deleteShipBooking(shipBooking.getShipBookIdx())).withRel(DELETE_SHIPBOOKING))
                                )
                                .collect(toList())
                ).add(linkTo(methodOn(ShipBookingController.class).getShipBookings()).withSelfRel())
        );
    }

    @ApiOperation(value = "선박 예약 개별 조회")
    @GetMapping(value = "/{shipBookingIdx}")
    public ResponseEntity<ShipBookingDto.Response> getShipBooking(@PathVariable("shipBookingIdx") Long shipBookingIdx) {
        var shipBooking = shipBookingService.getShipBooking(shipBookingIdx);
        return ResponseEntity.ok(
                new ShipBookingDto.Response(shipBooking)
                        .add(linkTo(methodOn(ShipBookingController.class).getShipBooking(shipBookingIdx)).withSelfRel())
                        .add(linkTo(ShipBookingController.class).slash(shipBookingIdx).withRel(UPDATE_SHIPBOOKING))
                        .add(linkTo(methodOn(ShipBookingController.class).deleteShipBooking(shipBookingIdx)).withRel(DELETE_SHIPBOOKING))
        );
    }

    @ApiOperation(value = "선박 예약 추가")
    @PostMapping
    public ResponseEntity<ShipBookingDto.Response> addShipBooking(@RequestBody ShipBookingDto.Request param) {
        var shipBooking = shipBookingService.addShipBooking(param.toEntity());
        return ResponseEntity.created(
                linkTo(methodOn(ShipBookingController.class).getShipBooking(shipBooking.getShipBookIdx())).withSelfRel().toUri()
        ).body(new ShipBookingDto.Response(shipBooking).add(linkTo(methodOn(ShipBookingController.class).getShipBookings()).withRel(ALL_SHIPBOOKINGS)));
    }

    @ApiOperation(value = "선박 예약 삭제")
    @DeleteMapping(value = "/{shipBookingIdx}")
    public ResponseEntity<ShipBookingDto.Response> deleteShipBooking(@PathVariable Long shipBookingIdx) {
        var shipBooking = shipBookingService.deleteShipBooking(shipBookingIdx);
        return ResponseEntity.ok(
                new ShipBookingDto.Response(shipBooking)
                        .add(linkTo(methodOn(ShipBookingController.class).getShipBookings()).withSelfRel())
        );
    }

}
