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
@Table(name = "businfo")
public class BusInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long businfoId;

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

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime modifiedAt;

    @Builder
    @PersistenceConstructor
    public BusInfo(Long businfoId, String routeId, String gradeNm, String depPlandTime, String arrPlandTime,
            String depPlaceNm, String arrPlaceNm, String charge, LocalDateTime createdAt, LocalDateTime modifiedAt) {

        this.businfoId = businfoId;
        this.routeId = routeId;
        this.gradeNm = gradeNm;
        this.depPlandTime = depPlandTime;
        this.arrPlandTime = arrPlandTime;
        this.depPlaceNm = depPlaceNm;
        this.arrPlaceNm = arrPlaceNm;
        this.charge = charge;
        this.createdAt = defaultIfNull(createdAt, now());
        this.modifiedAt = defaultIfNull(modifiedAt, now());
    }

}
