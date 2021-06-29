package com.mhkim.tms.controller.v1.bus;

import com.mhkim.tms.controller.v1.bus.dto.BusBookingDto;
import com.mhkim.tms.service.bus.BusBookingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(tags = {"BusBooking"})
@RequestMapping("/api/v1/buses/bookins")
@RequiredArgsConstructor
@RestController
public class BusBookingController {

    private static final String ALL_BUSBOOKINGS = "all-busBookings";

    private static final String GET_BUSBOOKING = "get-busBooking";

    private static final String UPDATE_BUSBOOKING = "update-busBooking";

    private static final String DELETE_BUSBOOKING = "delete-busBooking";

    private final BusBookingService busBookingService;

    @ApiOperation(value = "버스 예약 전체 조회")
    @GetMapping
    public ResponseEntity<CollectionModel<BusBookingDto.Response>> getBusBookings() {
        return ResponseEntity.ok(
                CollectionModel.of(
                        busBookingService.getBusBookings().stream()
                                .map(busBooking -> new BusBookingDto.Response(busBooking)
                                        .add(linkTo(methodOn(BusBookingController.class).getBusBooking(busBooking.getBusBookIdx())).withSelfRel())
                                        .add(linkTo(BusBookingController.class).slash(busBooking.getBusBookIdx()).withRel(UPDATE_BUSBOOKING))
                                        .add(linkTo(methodOn(BusBookingController.class).deleteBusBooking(busBooking.getBusBookIdx())).withRel(DELETE_BUSBOOKING))
                                )
                                .collect(toList())
                ).add(linkTo(methodOn(BusBookingController.class).getBusBookings()).withSelfRel())
        );
    }

    @ApiOperation(value = "예약자의 버스 예약 목록 조회")
    @GetMapping(value = "/{userIdx}/user")
    public ResponseEntity<CollectionModel<BusBookingDto.Response>> getBusBookingList(@PathVariable Long userIdx) {
        return ResponseEntity.ok(
                CollectionModel.of(
                        busBookingService.getBusBookingsByUserId(userIdx).stream()
                                .map(busBooking -> new BusBookingDto.Response(busBooking)
                                        .add(linkTo(methodOn(BusBookingController.class).getBusBooking(busBooking.getBusBookIdx())).withSelfRel())
                                        .add(linkTo(BusBookingController.class).slash(busBooking.getBusBookIdx()).withRel(UPDATE_BUSBOOKING))
                                        .add(linkTo(methodOn(BusBookingController.class).deleteBusBooking(busBooking.getBusBookIdx())).withRel(DELETE_BUSBOOKING))
                                )
                                .collect(toList())
                ).add(linkTo(methodOn(BusBookingController.class).getBusBookings()).withSelfRel())
        );
    }

    @ApiOperation(value = "버스 예약 개별 조회")
    @GetMapping(value = "/{busBookingIdx}")
    public ResponseEntity<BusBookingDto.Response> getBusBooking(@PathVariable("busBookingIdx") Long busBookingIdx) {
        var busBooking = busBookingService.getBusBooking(busBookingIdx);
        return ResponseEntity.ok(
                new BusBookingDto.Response(busBooking)
                        .add(linkTo(methodOn(BusBookingController.class).getBusBooking(busBookingIdx)).withSelfRel())
                        .add(linkTo(BusBookingController.class).slash(busBookingIdx).withRel(UPDATE_BUSBOOKING))
                        .add(linkTo(methodOn(BusBookingController.class).deleteBusBooking(busBookingIdx)).withRel(DELETE_BUSBOOKING))
        );
    }

    @ApiOperation(value = "버스 예약 추가")
    @PostMapping
    public ResponseEntity<BusBookingDto.Response> addBusBooking(@RequestBody BusBookingDto.Request param) {
        var busBooking = busBookingService.addBusBooking(param.toEntity());
        return ResponseEntity.created(
                linkTo(methodOn(BusBookingController.class).getBusBooking(busBooking.getBusBookIdx())).withSelfRel().toUri()
        ).body(new BusBookingDto.Response(busBooking).add(linkTo(methodOn(BusBookingController.class).getBusBookings()).withRel(ALL_BUSBOOKINGS)));
    }

    @ApiOperation(value = "버스 예약 삭제")
    @DeleteMapping(value = "/{busBookingIdx}")
    public ResponseEntity<BusBookingDto.Response> deleteBusBooking(@PathVariable Long busBookingIdx) {
        var busBooking = busBookingService.deleteBusBooking(busBookingIdx);
        return ResponseEntity.ok(
                new BusBookingDto.Response(busBooking)
                        .add(linkTo(methodOn(BusBookingController.class).getBusBookings()).withSelfRel())
        );
    }

}
