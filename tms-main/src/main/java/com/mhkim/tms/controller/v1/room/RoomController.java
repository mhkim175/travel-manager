package com.mhkim.tms.controller.v1.room;

import com.mhkim.tms.controller.v1.room.dto.RoomDto;
import com.mhkim.tms.service.room.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(tags = {"Room"})
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
@RestController
public class RoomController {
    
    private static final String ALL_ROOMS = "all-rooms";

    private static final String GET_ROOM = "get-room";

    private static final String UPDATE_ROOM = "update-room";

    private static final String DELETE_ROOM = "delete-room";

    private final RoomService roomService;

    @ApiOperation(value = "숙박 전체 조회")
    @GetMapping
    public ResponseEntity<CollectionModel<RoomDto.Response>> getRooms() {
        return ResponseEntity.ok(
                CollectionModel.of(
                        roomService.getRooms().stream()
                                .map(room -> new RoomDto.Response(room)
                                        .add(linkTo(methodOn(RoomController.class).getRoom(room.getRoomIdx())).withSelfRel())
                                        .add(linkTo(RoomController.class).slash(room.getRoomIdx()).withRel(UPDATE_ROOM))
                                        .add(linkTo(methodOn(RoomController.class).deleteRoom(room.getRoomIdx())).withRel(DELETE_ROOM))
                                )
                                .collect(toList())
                ).add(linkTo(methodOn(RoomController.class).getRooms()).withSelfRel())
        );
    }

    @ApiOperation(value = "숙박 개별 조회")
    @GetMapping(value = "/{roomIdx}")
    public ResponseEntity<RoomDto.Response> getRoom(@PathVariable("roomIdx") Long roomIdx) {
        var room = roomService.getRoom(roomIdx);
        return ResponseEntity.ok(
                new RoomDto.Response(room)
                        .add(linkTo(methodOn(RoomController.class).getRoom(roomIdx)).withSelfRel())
                        .add(linkTo(RoomController.class).slash(roomIdx).withRel(UPDATE_ROOM))
                        .add(linkTo(methodOn(RoomController.class).deleteRoom(roomIdx)).withRel(DELETE_ROOM))
        );
    }

    @ApiOperation(value = "숙박 추가")
    @PostMapping
    public ResponseEntity<RoomDto.Response> addRoom(@RequestBody RoomDto.Request param) {
        var room = roomService.addRoom(param.toEntity());
        return ResponseEntity.created(
                linkTo(methodOn(RoomController.class).getRoom(room.getRoomIdx())).withSelfRel().toUri()
        ).body(new RoomDto.Response(room).add(linkTo(methodOn(RoomController.class).getRooms()).withRel(ALL_ROOMS)));
    }

    @ApiOperation(value = "숙박 삭제")
    @DeleteMapping(value = "/{roomIdx}")
    public ResponseEntity<RoomDto.Response> deleteRoom(@PathVariable Long roomIdx) {
        var room = roomService.deleteRoom(roomIdx);
        return ResponseEntity.ok(
                new RoomDto.Response(room)
                        .add(linkTo(methodOn(RoomController.class).getRooms()).withSelfRel())
        );
    }

}
