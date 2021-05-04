package com.mhkim.tms.v1.travelinfo.entity.bus;

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
public class Bus extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busIdx;

    @Column(length = 30)
    private String routeId;

    @Column(length = 10)
    private String gradeNm;

    @Column(length = 14)
    private String depPlandTime;

    @Column(length = 14)
    private String arrPlandTime;

    @Column(length = 100)
    private String depPlaceNm;

    @Column(length = 100)
    private String arrPlaceNm;

    @Column(length = 8)
    private String charge;

    @Builder
    public Bus(Long busIdx, String routeId, String gradeNm, String depPlandTime, String arrPlandTime, String depPlaceNm, String arrPlaceNm, String charge) {
        this.busIdx = busIdx;
        this.routeId = routeId;
        this.gradeNm = gradeNm;
        this.depPlandTime = depPlandTime;
        this.arrPlandTime = arrPlandTime;
        this.depPlaceNm = depPlaceNm;
        this.arrPlaceNm = arrPlaceNm;
        this.charge = charge;
    }

}
