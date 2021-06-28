package com.mhkim.tms.entity.ship;

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
public class Ship extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipIdx;

    @Column(length = 9, unique = true)
    private String vihicleNm;

    @Column(length = 100)
    private String depPlaceNm;

    @Column(length = 100)
    private String arrPlaceNm;

    @Column(length = 14)
    private String depPlandTime;

    @Column(length = 14)
    private String arrPlandTime;

    @Column(length = 8)
    private String charge;

    public Ship(Long shipIdx) {
        this.shipIdx = shipIdx;
    }

    @Builder
    public Ship(Long shipIdx, String vihicleNm, String depPlaceNm, String arrPlaceNm, String depPlandTime, String arrPlandTime, String charge) {
        this.shipIdx = shipIdx;
        this.vihicleNm = vihicleNm;
        this.depPlaceNm = depPlaceNm;
        this.arrPlaceNm = arrPlaceNm;
        this.depPlandTime = depPlandTime;
        this.arrPlandTime = arrPlandTime;
        this.charge = charge;
    }

}
