package com.mhkim.tms.controller.v1.room;

import com.mhkim.tms.advice.exception.CDataNotFoundException;
import com.mhkim.tms.controller.v1.room.dto.RoomBookingDto;
import com.mhkim.tms.entity.room.RoomBooking;
import com.mhkim.tms.service.room.RoomBookingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Api(tags = {"2. Booking-Room"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/booking")
public class RoomBookingController {

    private final RoomBookingService roombookingService;

    @ApiOperation(value = "예약숙소 목록 조회")
    @GetMapping(value = "/rooms")
    public ResponseEntity<List<RoomBookingDto.Response>> getRoomBookingList() {
        return ResponseEntity.ok(
                roombookingService.getRoomBookingList().stream()
                        .map(RoomBookingDto.Response::new)
                        .collect(toList())
        );
    }

    @ApiOperation(value = "예약자의 예약숙소 목록 조회")
    @GetMapping(value = "/room")
    public ResponseEntity<List<RoomBookingDto.Response>> getRoomBookingList(@RequestBody RoomBookingDto.Request param) {
        return ResponseEntity.ok(
                roombookingService.getRoomBookingByUserId(param.getUserIdx()).stream()
                        .map(RoomBookingDto.Response::new)
                        .collect(toList())
        );
    }

    @ApiOperation(value = "예약숙소 조회")
    @GetMapping(value = "/room/{roomBookIdx}")
    public ResponseEntity<RoomBookingDto.Response> getRoomBooking(@PathVariable("roomBookIdx") Long roomBookIdx) {
        return ResponseEntity.ok(
                roombookingService.getRoomBooking(roomBookIdx)
                        .map(RoomBookingDto.Response::new)
                        .orElseThrow(() -> new CDataNotFoundException("RoomBooking not found"))
        );
    }

    @ApiOperation(value = "숙소 예약")
    @PostMapping(value = "/room/book")
    public ResponseEntity<RoomBookingDto.Response> bookRoom(@RequestBody RoomBookingDto.Book param) {
        RoomBooking roomBooking = roombookingService.bookRoom(param.getBookDate(), param.getRoomIdx(), param.getUserIdx());
        return new ResponseEntity<>(new RoomBookingDto.Response(roomBooking), HttpStatus.CREATED);
    }

    @ApiOperation(value = "예약숙소 삭제")
    @DeleteMapping(value = "/room")
    public ResponseEntity<RoomBookingDto.Response> deleteQna(@RequestBody RoomBookingDto.Request param) {
        RoomBooking roomBooking = roombookingService.deleteRoomBooking(param.getRoomBookIdx());
        return ResponseEntity.ok(new RoomBookingDto.Response(roomBooking));
    }

}
