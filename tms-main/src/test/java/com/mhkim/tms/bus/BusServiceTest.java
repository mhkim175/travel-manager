package com.mhkim.tms.bus;

import com.mhkim.tms.entity.bus.Bus;
import com.mhkim.tms.exception.error.NotFoundException;
import com.mhkim.tms.repository.bus.BusRepository;
import com.mhkim.tms.service.bus.BusService;
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
public class BusServiceTest {

    @InjectMocks
    private BusService busService;

    @Mock
    private BusRepository busRepository;

    @Mock
    private MessageSourceAccessor messageSourceAccessor;

    private Bus expectedBus;

    @BeforeEach
    void setUp() {
        MessageUtils.setMessageSourceAccessor(messageSourceAccessor);

        expectedBus = Bus.builder()
                .busIdx(1L)
                .routeId("ZAEK010301")
                .gradeNm("우등")
                .depPlandTime("202001010600")
                .arrPlandTime("202001010800")
                .depPlaceNm("서울고속터미널")
                .arrPlaceNm("대전고속버스터미널")
                .charge("14000")
                .build();
    }

    @DisplayName(value = "버스 전체 조회")
    @Test
    void getBuses() {
        // given
        given(busRepository.findAll()).willReturn(List.of(expectedBus));

        // when
        List<Bus> buses = busService.getBuses();

        // then
        assertThat(buses.size()).isEqualTo(1);
        assertThat(buses.get(0).getBusIdx()).isEqualTo(expectedBus.getBusIdx());
        assertThat(buses.get(0).getRouteId()).isEqualTo(expectedBus.getRouteId());
        assertThat(buses.get(0).getGradeNm()).isEqualTo(expectedBus.getGradeNm());
        assertThat(buses.get(0).getDepPlandTime()).isEqualTo(expectedBus.getDepPlandTime());
        assertThat(buses.get(0).getArrPlandTime()).isEqualTo(expectedBus.getArrPlandTime());
        assertThat(buses.get(0).getDepPlaceNm()).isEqualTo(expectedBus.getDepPlaceNm());
        assertThat(buses.get(0).getArrPlaceNm()).isEqualTo(expectedBus.getArrPlaceNm());
        assertThat(buses.get(0).getCharge()).isEqualTo(expectedBus.getCharge());

        log.info("buses: {}", buses);
    }

    @DisplayName(value = "버스 개별 조회 성공")
    @Test
    void getBusSuccess() {
        // given
        given(busRepository.findById(anyLong())).willReturn(Optional.of(expectedBus));

        // when
        Bus bus = busService.getBus(1L);

        // then
        assertThat(bus).isNotNull();
        assertThat(bus.getBusIdx()).isEqualTo(expectedBus.getBusIdx());
        assertThat(bus.getRouteId()).isEqualTo(expectedBus.getRouteId());
        assertThat(bus.getGradeNm()).isEqualTo(expectedBus.getGradeNm());
        assertThat(bus.getDepPlandTime()).isEqualTo(expectedBus.getDepPlandTime());
        assertThat(bus.getArrPlandTime()).isEqualTo(expectedBus.getArrPlandTime());
        assertThat(bus.getDepPlaceNm()).isEqualTo(expectedBus.getDepPlaceNm());
        assertThat(bus.getArrPlaceNm()).isEqualTo(expectedBus.getArrPlaceNm());
        assertThat(bus.getCharge()).isEqualTo(expectedBus.getCharge());

        log.info("bus: {}", bus);
    }

    @DisplayName(value = "버스 개별 조회 실패")
    @Test
    void Fail() {
        // given
        given(busRepository.findById(anyLong())).willReturn(Optional.of(null));

        // then
        assertThrows(NotFoundException.class, () -> {
            busService.getBus(100L);
        });
    }

    @DisplayName(value = "버스 추가 성공")
    @Test
    void addBusSuccess() {
        // given
        given(busRepository.save(any(Bus.class))).willReturn(expectedBus);

        // when
        Bus bus = busService.addBus(expectedBus);

        // then
        assertThat(bus).isNotNull();
        assertThat(bus.getBusIdx()).isEqualTo(expectedBus.getBusIdx());
        assertThat(bus.getRouteId()).isEqualTo(expectedBus.getRouteId());
        assertThat(bus.getGradeNm()).isEqualTo(expectedBus.getGradeNm());
        assertThat(bus.getDepPlandTime()).isEqualTo(expectedBus.getDepPlandTime());
        assertThat(bus.getArrPlandTime()).isEqualTo(expectedBus.getArrPlandTime());
        assertThat(bus.getDepPlaceNm()).isEqualTo(expectedBus.getDepPlaceNm());
        assertThat(bus.getArrPlaceNm()).isEqualTo(expectedBus.getArrPlaceNm());
        assertThat(bus.getCharge()).isEqualTo(expectedBus.getCharge());

        log.info("bus: {}", bus);
    }
    
    @DisplayName(value = "버스 삭제 성공")
    @Test
    void deleteBusSuccess() {
        // given
        given(busRepository.findById(anyLong())).willReturn(Optional.of(expectedBus));

        // when
        Bus bus = busService.deleteBus(1L);

        // then
        assertThat(bus).isNotNull();
        assertThat(bus.getBusIdx()).isEqualTo(expectedBus.getBusIdx());
        assertThat(bus.getRouteId()).isEqualTo(expectedBus.getRouteId());
        assertThat(bus.getGradeNm()).isEqualTo(expectedBus.getGradeNm());
        assertThat(bus.getDepPlandTime()).isEqualTo(expectedBus.getDepPlandTime());
        assertThat(bus.getArrPlandTime()).isEqualTo(expectedBus.getArrPlandTime());
        assertThat(bus.getDepPlaceNm()).isEqualTo(expectedBus.getDepPlaceNm());
        assertThat(bus.getArrPlaceNm()).isEqualTo(expectedBus.getArrPlaceNm());
        assertThat(bus.getCharge()).isEqualTo(expectedBus.getCharge());

        log.info("bus: {}", bus);
    }

    @DisplayName(value = "버스 삭제 실패")
    @Test
    void deleteBusFail() {
        // given
        given(busRepository.findById(anyLong())).willReturn(Optional.of(null));

        // then
        assertThrows(NotFoundException.class, () -> {
           busService.deleteBus(100L);
        });
    }

}
