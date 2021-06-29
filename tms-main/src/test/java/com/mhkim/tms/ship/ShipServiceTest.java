package com.mhkim.tms.ship;

import com.mhkim.tms.entity.ship.Ship;
import com.mhkim.tms.exception.error.NotFoundException;
import com.mhkim.tms.repository.ship.ShipRepository;
import com.mhkim.tms.service.ship.ShipService;
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
public class ShipServiceTest {

    @InjectMocks
    private ShipService shipService;

    @Mock
    private ShipRepository shipRepository;

    @Mock
    private MessageSourceAccessor messageSourceAccessor;

    private Ship expectedShip;

    @BeforeEach
    void setUp() {
        MessageUtils.setMessageSourceAccessor(messageSourceAccessor);

        expectedShip = Ship.builder()
                .shipIdx(1L)
                .vihicleNm("일이삼페리호")
                .depPlaceNm("여수")
                .arrPlaceNm("개도")
                .depPlandTime("202012010600")
                .arrPlandTime("202012010730")
                .charge("9600")
                .build();
    }

    @DisplayName(value = "선박 전체 조회")
    @Test
    void getShips() {
        // given
        given(shipRepository.findAll()).willReturn(List.of(expectedShip));

        // when
        List<Ship> ships = shipService.getShips();

        // then
        assertThat(ships.size()).isEqualTo(1);
        assertThat(ships.get(0).getShipIdx()).isEqualTo(expectedShip.getShipIdx());
        assertThat(ships.get(0).getVihicleNm()).isEqualTo(expectedShip.getVihicleNm());
        assertThat(ships.get(0).getDepPlaceNm()).isEqualTo(expectedShip.getDepPlaceNm());
        assertThat(ships.get(0).getArrPlaceNm()).isEqualTo(expectedShip.getArrPlaceNm());
        assertThat(ships.get(0).getDepPlandTime()).isEqualTo(expectedShip.getDepPlandTime());
        assertThat(ships.get(0).getArrPlandTime()).isEqualTo(expectedShip.getArrPlandTime());
        assertThat(ships.get(0).getCharge()).isEqualTo(expectedShip.getCharge());

        log.info("ships: {}", ships);
    }

    @DisplayName(value = "선박 개별 조회 성공")
    @Test
    void getShipSuccess() {
        // given
        given(shipRepository.findById(anyLong())).willReturn(Optional.of(expectedShip));

        // when
        Ship ship = shipService.getShip(1L);

        // then
        assertThat(ship).isNotNull();
        assertThat(ship.getShipIdx()).isEqualTo(expectedShip.getShipIdx());
        assertThat(ship.getVihicleNm()).isEqualTo(expectedShip.getVihicleNm());
        assertThat(ship.getDepPlaceNm()).isEqualTo(expectedShip.getDepPlaceNm());
        assertThat(ship.getArrPlaceNm()).isEqualTo(expectedShip.getArrPlaceNm());
        assertThat(ship.getDepPlandTime()).isEqualTo(expectedShip.getDepPlandTime());
        assertThat(ship.getArrPlandTime()).isEqualTo(expectedShip.getArrPlandTime());
        assertThat(ship.getCharge()).isEqualTo(expectedShip.getCharge());

        log.info("ship: {}", ship);
    }

    @DisplayName(value = "선박 개별 조회 실패")
    @Test
    void Fail() {
        // given
        given(shipRepository.findById(anyLong())).willReturn(Optional.of(null));

        // then
        assertThrows(NotFoundException.class, () -> {
            shipService.getShip(100L);
        });
    }

    @DisplayName(value = "선박 추가 성공")
    @Test
    void addShipSuccess() {
        // given
        given(shipRepository.save(any(Ship.class))).willReturn(expectedShip);

        // when
        Ship ship = shipService.addShip(expectedShip);

        // then
        assertThat(ship).isNotNull();
        assertThat(ship.getShipIdx()).isEqualTo(expectedShip.getShipIdx());
        assertThat(ship.getVihicleNm()).isEqualTo(expectedShip.getVihicleNm());
        assertThat(ship.getDepPlaceNm()).isEqualTo(expectedShip.getDepPlaceNm());
        assertThat(ship.getArrPlaceNm()).isEqualTo(expectedShip.getArrPlaceNm());
        assertThat(ship.getDepPlandTime()).isEqualTo(expectedShip.getDepPlandTime());
        assertThat(ship.getArrPlandTime()).isEqualTo(expectedShip.getArrPlandTime());
        assertThat(ship.getCharge()).isEqualTo(expectedShip.getCharge());

        log.info("ship: {}", ship);
    }
    
    @DisplayName(value = "선박 삭제 성공")
    @Test
    void deleteShipSuccess() {
        // given
        given(shipRepository.findById(anyLong())).willReturn(Optional.of(expectedShip));

        // when
        Ship ship = shipService.deleteShip(1L);

        // then
        assertThat(ship).isNotNull();
        assertThat(ship.getShipIdx()).isEqualTo(expectedShip.getShipIdx());
        assertThat(ship.getVihicleNm()).isEqualTo(expectedShip.getVihicleNm());
        assertThat(ship.getDepPlaceNm()).isEqualTo(expectedShip.getDepPlaceNm());
        assertThat(ship.getArrPlaceNm()).isEqualTo(expectedShip.getArrPlaceNm());
        assertThat(ship.getDepPlandTime()).isEqualTo(expectedShip.getDepPlandTime());
        assertThat(ship.getArrPlandTime()).isEqualTo(expectedShip.getArrPlandTime());
        assertThat(ship.getCharge()).isEqualTo(expectedShip.getCharge());

        log.info("ship: {}", ship);
    }

    @DisplayName(value = "선박 삭제 실패")
    @Test
    void deleteShipFail() {
        // given
        given(shipRepository.findById(anyLong())).willReturn(Optional.of(null));

        // then
        assertThrows(NotFoundException.class, () -> {
           shipService.deleteShip(100L);
        });
    }

}
