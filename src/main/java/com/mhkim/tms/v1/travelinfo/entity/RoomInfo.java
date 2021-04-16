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
@Table(name = "roominfo")
public class RoomInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roominfoId;

    @Column(length = 100)
    private String name;

    @Column(length = 5)
    private String count;

    @Column(length = 14)
    private String checkIn;

    @Column(length = 14)
    private String checkOut;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime modifiedAt;

    @Builder
    @PersistenceConstructor
    public RoomInfo(Long roominfoId, String name, String count, String checkIn, String checkOut,
            LocalDateTime createdAt, LocalDateTime modifiedAt) {

        this.roominfoId = roominfoId;
        this.name = name;
        this.count = count;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.createdAt = defaultIfNull(createdAt, now());
        this.modifiedAt = defaultIfNull(modifiedAt, now());
    }

}
