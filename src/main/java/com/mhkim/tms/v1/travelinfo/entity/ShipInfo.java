package com.mhkim.tms.v1.travelinfo.entity;

import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.PersistenceConstructor;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "shipinfo")
public class ShipInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipinfoId;

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

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime modifiedAt;

    @Builder
    @PersistenceConstructor
    public ShipInfo(Long shipinfoId, String vihicleNm, String depPlaceNm, String arrPlaceNm, String depPlandTime,
            String arrPlandTime, String charge, LocalDateTime createdAt, LocalDateTime modifiedAt) {

        this.shipinfoId = shipinfoId;
        this.vihicleNm = vihicleNm;
        this.depPlaceNm = depPlaceNm;
        this.arrPlaceNm = arrPlaceNm;
        this.depPlandTime = depPlandTime;
        this.arrPlandTime = arrPlandTime;
        this.charge = charge;
        this.createdAt = defaultIfNull(createdAt, now());
        this.modifiedAt = defaultIfNull(modifiedAt, now());
    }

}
