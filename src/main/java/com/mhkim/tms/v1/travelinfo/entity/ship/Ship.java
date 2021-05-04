package com.mhkim.tms.v1.travelinfo.entity.ship;

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
public class Ship extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipIdx;

    @Column(length = 9)
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
