package com.mhkim.tms.entity.flight;

import com.mhkim.tms.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@DynamicUpdate
@Entity
@Getter
@NoArgsConstructor
@ToString
public class Flight extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightIdx;

    @Column(length = 9, unique = true)
    private String vihicleId;

    @Column(length = 100)
    private String airlineNm;

    @Column(length = 30)
    private String arrAirportNm;

    @Column(length = 14)
    private String arrPlandTime;

    @Column(length = 30)
    private String depAirportNm;

    @Column(length = 14)
    private String depPlandTime;

    public Flight(Long flightIdx) {
        this.flightIdx = flightIdx;
    }

    @Builder
    public Flight(Long flightIdx, String vihicleId, String airlineNm, String arrAirportNm, String arrPlandTime, String depAirportNm, String depPlandTime) {
        this.flightIdx = flightIdx;
        this.vihicleId = vihicleId;
        this.airlineNm = airlineNm;
        this.arrAirportNm = arrAirportNm;
        this.arrAirportNm = arrPlandTime;
        this.depAirportNm = depAirportNm;
        this.depPlandTime = depPlandTime;
    }

}
