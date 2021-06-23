package com.mhkim.tms.entity.flight;

import com.mhkim.tms.entity.BaseTimeEntity;
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
public class Airport extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airportIdx;

    @Column(length = 9)
    private String airportId;

    @Column(length = 100)
    private String airportNm;

    @Builder
    public Airport(Long airportIdx, String airportId, String airportNm) {
        this.airportIdx = airportIdx;
        this.airportId = airportId;
        this.airportNm = airportNm;
    }

}
