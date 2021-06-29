package com.mhkim.tms.room;

import com.mhkim.tms.entity.room.Room;
import com.mhkim.tms.exception.error.NotFoundException;
import com.mhkim.tms.repository.room.RoomRepository;
import com.mhkim.tms.service.room.RoomService;
import com.mhkim.tms.util.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class RoomServiceTest {

    @InjectMocks
    private RoomService roomService;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private MessageSourceAccessor messageSourceAccessor;

    private Room expectedRoom;

    @BeforeEach
    void setUp() {
        MessageUtils.setMessageSourceAccessor(messageSourceAccessor);

        expectedRoom = Room.builder()
                .roomIdx(1L)
                .name("일이삼리조트 서귀포")
                .count("224")
                .checkIn("15:00")
                .checkOut("11:00")
                .build();
    }

    @DisplayName(value = "숙박 전체 조회")
    @Test
    void getRooms() {
        // given
        given(roomRepository.findAll()).willReturn(List.of(expectedRoom));

        // when
        List<Room> rooms = roomService.getRooms();

        // then
        assertThat(rooms.size()).isEqualTo(1);
        assertThat(rooms.get(0).getRoomIdx()).isEqualTo(expectedRoom.getRoomIdx());
        assertThat(rooms.get(0).getName()).isEqualTo(expectedRoom.getName());
        assertThat(rooms.get(0).getCount()).isEqualTo(expectedRoom.getCount());
        assertThat(rooms.get(0).getCheckIn()).isEqualTo(expectedRoom.getCheckIn());
        assertThat(rooms.get(0).getCheckOut()).isEqualTo(expectedRoom.getCheckOut());

        log.info("rooms: {}", rooms);
    }

    @DisplayName(value = "숙박 개별 조회 성공")
    @Test
    void getRoomSuccess() {
        // given
        given(roomRepository.findById(anyLong())).willReturn(Optional.of(expectedRoom));

        // when
        Room room = roomService.getRoom(1L);

        // then
        assertThat(room).isNotNull();
        assertThat(room.getRoomIdx()).isEqualTo(expectedRoom.getRoomIdx());
        assertThat(room.getName()).isEqualTo(expectedRoom.getName());
        assertThat(room.getCount()).isEqualTo(expectedRoom.getCount());
        assertThat(room.getCheckIn()).isEqualTo(expectedRoom.getCheckIn());
        assertThat(room.getCheckOut()).isEqualTo(expectedRoom.getCheckOut());

        log.info("room: {}", room);
    }

    @DisplayName(value = "숙박 개별 조회 실패")
    @Test
    void Fail() {
        // given
        given(roomRepository.findById(anyLong())).willReturn(Optional.of(null));

        // then
        assertThrows(NotFoundException.class, () -> {
            roomService.getRoom(100L);
        });
    }

    @DisplayName(value = "숙박 추가 성공")
    @Test
    void addRoomSuccess() {
        // given
        given(roomRepository.save(any(Room.class))).willReturn(expectedRoom);

        // when
        Room room = roomService.addRoom(expectedRoom);

        // then
        assertThat(room).isNotNull();
        assertThat(room.getRoomIdx()).isEqualTo(expectedRoom.getRoomIdx());
        assertThat(room.getName()).isEqualTo(expectedRoom.getName());
        assertThat(room.getCount()).isEqualTo(expectedRoom.getCount());
        assertThat(room.getCheckIn()).isEqualTo(expectedRoom.getCheckIn());
        assertThat(room.getCheckOut()).isEqualTo(expectedRoom.getCheckOut());

        log.info("room: {}", room);
    }
    
    @DisplayName(value = "숙박 삭제 성공")
    @Test
    void deleteRoomSuccess() {
        // given
        given(roomRepository.findById(anyLong())).willReturn(Optional.of(expectedRoom));

        // when
        Room room = roomService.deleteRoom(1L);

        // then
        assertThat(room).isNotNull();
        assertThat(room.getRoomIdx()).isEqualTo(expectedRoom.getRoomIdx());
        assertThat(room.getName()).isEqualTo(expectedRoom.getName());
        assertThat(room.getCount()).isEqualTo(expectedRoom.getCount());
        assertThat(room.getCheckIn()).isEqualTo(expectedRoom.getCheckIn());
        assertThat(room.getCheckOut()).isEqualTo(expectedRoom.getCheckOut());

        log.info("room: {}", room);
    }

    @DisplayName(value = "숙박 삭제 실패")
    @Test
    void deleteRoomFail() {
        // given
        given(roomRepository.findById(anyLong())).willReturn(Optional.of(null));

        // then
        assertThrows(NotFoundException.class, () -> {
           roomService.deleteRoom(100L);
        });
    }

}
