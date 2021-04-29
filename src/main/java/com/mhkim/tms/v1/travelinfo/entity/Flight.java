package com.mhkim.tms.v1.travelinfo.entity;

import com.mhkim.tms.common.BaseTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@ToString
@DynamicUpdate
@Entity
@Table(name = "flight")
public class Flight extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

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

    @Column(length = 9)
    private String vihicleId;

    @Builder
    public Flight(Long flightId, String airlineNm, String arrAirportNm, String arrPlandTime, String depAirportNm, String depPlandTime, String vihicleId) {
        this.flightId = flightId;
        this.airlineNm = airlineNm;
        this.arrAirportNm = arrAirportNm;
        this.arrAirportNm = arrPlandTime;
        this.depAirportNm = depAirportNm;
        this.depPlandTime = depPlandTime;
        this.vihicleId = vihicleId;
    }

}
