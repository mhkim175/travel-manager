package com.mhkim.tms.flight;

import com.mhkim.tms.entity.flight.Flight;
import com.mhkim.tms.exception.error.NotFoundException;
import com.mhkim.tms.repository.flight.FlightRepository;
import com.mhkim.tms.service.flight.FlightService;
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
public class FlightServiceTest {

    @InjectMocks
    private FlightService flightService;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private MessageSourceAccessor messageSourceAccessor;

    private Flight expectedFlight;

    @BeforeEach
    void setUp() {
        MessageUtils.setMessageSourceAccessor(messageSourceAccessor);

        expectedFlight = Flight.builder()
                .flightIdx(1L)
                .vihicleId("QZ8142")
                .airlineNm("아시아나항공")
                .arrAirportNm("광주")
                .arrPlandTime("20201201082000")
                .depAirportNm("제주")
                .depPlandTime("20201201090500")
                .build();
    }

    @DisplayName(value = "항공편 전체 조회")
    @Test
    void getFlights() {
        // given
        given(flightRepository.findAll()).willReturn(List.of(expectedFlight));

        // when
        List<Flight> flights = flightService.getFlights();

        // then
        assertThat(flights.size()).isEqualTo(1);
        assertThat(flights.get(0).getFlightIdx()).isEqualTo(expectedFlight.getFlightIdx());
        assertThat(flights.get(0).getVihicleId()).isEqualTo(expectedFlight.getVihicleId());
        assertThat(flights.get(0).getAirlineNm()).isEqualTo(expectedFlight.getAirlineNm());
        assertThat(flights.get(0).getArrAirportNm()).isEqualTo(expectedFlight.getArrAirportNm());
        assertThat(flights.get(0).getArrPlandTime()).isEqualTo(expectedFlight.getArrPlandTime());
        assertThat(flights.get(0).getDepAirportNm()).isEqualTo(expectedFlight.getDepAirportNm());
        assertThat(flights.get(0).getDepPlandTime()).isEqualTo(expectedFlight.getDepPlandTime());

        log.info("flights: {}", flights);
    }

    @DisplayName(value = "항공편 개별 조회 성공")
    @Test
    void getFlightSuccess() {
        // given
        given(flightRepository.findById(anyLong())).willReturn(Optional.of(expectedFlight));

        // when
        Flight flight = flightService.getFlight(1L);

        // then
        assertThat(flight).isNotNull();
        assertThat(flight.getFlightIdx()).isEqualTo(expectedFlight.getFlightIdx());
        assertThat(flight.getVihicleId()).isEqualTo(expectedFlight.getVihicleId());
        assertThat(flight.getAirlineNm()).isEqualTo(expectedFlight.getAirlineNm());
        assertThat(flight.getArrAirportNm()).isEqualTo(expectedFlight.getArrAirportNm());
        assertThat(flight.getArrPlandTime()).isEqualTo(expectedFlight.getArrPlandTime());
        assertThat(flight.getDepAirportNm()).isEqualTo(expectedFlight.getDepAirportNm());
        assertThat(flight.getDepPlandTime()).isEqualTo(expectedFlight.getDepPlandTime());

        log.info("flight: {}", flight);
    }

    @DisplayName(value = "항공편 개별 조회 실패")
    @Test
    void Fail() {
        // given
        given(flightRepository.findById(anyLong())).willReturn(Optional.of(null));

        // then
        assertThrows(NotFoundException.class, () -> {
            flightService.getFlight(100L);
        });
    }

    @DisplayName(value = "항공편 추가 성공")
    @Test
    void addFlightSuccess() {
        // given
        given(flightRepository.save(any(Flight.class))).willReturn(expectedFlight);

        // when
        Flight flight = flightService.addFlight(expectedFlight);

        // then
        assertThat(flight).isNotNull();
        assertThat(flight.getFlightIdx()).isEqualTo(expectedFlight.getFlightIdx());
        assertThat(flight.getVihicleId()).isEqualTo(expectedFlight.getVihicleId());
        assertThat(flight.getAirlineNm()).isEqualTo(expectedFlight.getAirlineNm());
        assertThat(flight.getArrAirportNm()).isEqualTo(expectedFlight.getArrAirportNm());
        assertThat(flight.getArrPlandTime()).isEqualTo(expectedFlight.getArrPlandTime());
        assertThat(flight.getDepAirportNm()).isEqualTo(expectedFlight.getDepAirportNm());
        assertThat(flight.getDepPlandTime()).isEqualTo(expectedFlight.getDepPlandTime());

        log.info("flight: {}", flight);
    }
    
    @DisplayName(value = "항공편 삭제 성공")
    @Test
    void deleteFlightSuccess() {
        // given
        given(flightRepository.findById(anyLong())).willReturn(Optional.of(expectedFlight));

        // when
        Flight flight = flightService.deleteFlight(1L);

        // then
        assertThat(flight).isNotNull();
        assertThat(flight.getFlightIdx()).isEqualTo(expectedFlight.getFlightIdx());
        assertThat(flight.getVihicleId()).isEqualTo(expectedFlight.getVihicleId());
        assertThat(flight.getAirlineNm()).isEqualTo(expectedFlight.getAirlineNm());
        assertThat(flight.getArrAirportNm()).isEqualTo(expectedFlight.getArrAirportNm());
        assertThat(flight.getArrPlandTime()).isEqualTo(expectedFlight.getArrPlandTime());
        assertThat(flight.getDepAirportNm()).isEqualTo(expectedFlight.getDepAirportNm());
        assertThat(flight.getDepPlandTime()).isEqualTo(expectedFlight.getDepPlandTime());

        log.info("flight: {}", flight);
    }

    @DisplayName(value = "항공편 삭제 실패")
    @Test
    void deleteFlightFail() {
        // given
        given(flightRepository.findById(anyLong())).willReturn(Optional.of(null));

        // then
        assertThrows(NotFoundException.class, () -> {
           flightService.deleteFlight(100L);
        });
    }

}
