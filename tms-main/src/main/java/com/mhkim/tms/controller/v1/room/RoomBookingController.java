package com.mhkim.tms.controller.v1.room;

import com.mhkim.tms.controller.v1.room.dto.RoomBookingDto;
import com.mhkim.tms.service.room.RoomBookingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(tags = {"RoomBooking"})
@RequestMapping("/api/v1/rooms/bookins")
@RequiredArgsConstructor
@RestController
public class RoomBookingController {

    private static final String ALL_ROOMBOOKINGS = "all-roomBookings";

    private static final String GET_ROOMBOOKING = "get-roomBooking";

    private static final String UPDATE_ROOMBOOKING = "update-roomBooking";

    private static final String DELETE_ROOMBOOKING = "delete-roomBooking";

    private final RoomBookingService roomBookingService;

    @ApiOperation(value = "숙박 예약 전체 조회")
    @GetMapping
    public ResponseEntity<CollectionModel<RoomBookingDto.Response>> getRoomBookings() {
        return ResponseEntity.ok(
                CollectionModel.of(
                        roomBookingService.getRoomBookings().stream()
                                .map(roomBooking -> new RoomBookingDto.Response(roomBooking)
                                        .add(linkTo(methodOn(RoomBookingController.class).getRoomBooking(roomBooking.getRoomBookIdx())).withSelfRel())
                                        .add(linkTo(RoomBookingController.class).slash(roomBooking.getRoomBookIdx()).withRel(UPDATE_ROOMBOOKING))
                                        .add(linkTo(methodOn(RoomBookingController.class).deleteRoomBooking(roomBooking.getRoomBookIdx())).withRel(DELETE_ROOMBOOKING))
                                )
                                .collect(toList())
                ).add(linkTo(methodOn(RoomBookingController.class).getRoomBookings()).withSelfRel())
        );
    }

    @ApiOperation(value = "예약자의 숙박 예약 목록 조회")
    @GetMapping(value = "/{userIdx}/user")
    public ResponseEntity<CollectionModel<RoomBookingDto.Response>> getRoomBookingList(@PathVariable Long userIdx) {
        return ResponseEntity.ok(
                CollectionModel.of(
                        roomBookingService.getRoomBookingsByUserId(userIdx).stream()
                                .map(roomBooking -> new RoomBookingDto.Response(roomBooking)
                                        .add(linkTo(methodOn(RoomBookingController.class).getRoomBooking(roomBooking.getRoomBookIdx())).withSelfRel())
                                        .add(linkTo(RoomBookingController.class).slash(roomBooking.getRoomBookIdx()).withRel(UPDATE_ROOMBOOKING))
                                        .add(linkTo(methodOn(RoomBookingController.class).deleteRoomBooking(roomBooking.getRoomBookIdx())).withRel(DELETE_ROOMBOOKING))
                                )
                                .collect(toList())
                ).add(linkTo(methodOn(RoomBookingController.class).getRoomBookings()).withSelfRel())
        );
    }

    @ApiOperation(value = "숙박 예약 개별 조회")
    @GetMapping(value = "/{roomBookingIdx}")
    public ResponseEntity<RoomBookingDto.Response> getRoomBooking(@PathVariable("roomBookingIdx") Long roomBookingIdx) {
        var roomBooking = roomBookingService.getRoomBooking(roomBookingIdx);
        return ResponseEntity.ok(
                new RoomBookingDto.Response(roomBooking)
                        .add(linkTo(methodOn(RoomBookingController.class).getRoomBooking(roomBookingIdx)).withSelfRel())
                        .add(linkTo(RoomBookingController.class).slash(roomBookingIdx).withRel(UPDATE_ROOMBOOKING))
                        .add(linkTo(methodOn(RoomBookingController.class).deleteRoomBooking(roomBookingIdx)).withRel(DELETE_ROOMBOOKING))
        );
    }

    @ApiOperation(value = "숙박 예약 추가")
    @PostMapping
    public ResponseEntity<RoomBookingDto.Response> addRoomBooking(@RequestBody RoomBookingDto.Request param) {
        var roomBooking = roomBookingService.addRoomBooking(param.toEntity());
        return ResponseEntity.created(
                linkTo(methodOn(RoomBookingController.class).getRoomBooking(roomBooking.getRoomBookIdx())).withSelfRel().toUri()
        ).body(new RoomBookingDto.Response(roomBooking).add(linkTo(methodOn(RoomBookingController.class).getRoomBookings()).withRel(ALL_ROOMBOOKINGS)));
    }

    @ApiOperation(value = "숙박 예약 삭제")
    @DeleteMapping(value = "/{roomBookingIdx}")
    public ResponseEntity<RoomBookingDto.Response> deleteRoomBooking(@PathVariable Long roomBookingIdx) {
        var roomBooking = roomBookingService.deleteRoomBooking(roomBookingIdx);
        return ResponseEntity.ok(
                new RoomBookingDto.Response(roomBooking)
                        .add(linkTo(methodOn(RoomBookingController.class).getRoomBookings()).withSelfRel())
        );
    }

}
