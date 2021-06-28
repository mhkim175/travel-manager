package com.mhkim.tms.entity.room;

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
public class Room extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomIdx;

    @Column(length = 100)
    private String name;

    @Column(length = 5)
    private String count;

    @Column(length = 14)
    private String checkIn;

    @Column(length = 14)
    private String checkOut;

    @Builder
    public Room(Long roomIdx, String name, String count, String checkIn, String checkOut) {
        this.roomIdx = roomIdx;
        this.name = name;
        this.count = count;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

}
