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
@Table(name = "room")
public class Room extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Column(length = 100)
    private String name;

    @Column(length = 5)
    private String count;

    @Column(length = 14)
    private String checkIn;

    @Column(length = 14)
    private String checkOut;

    @Builder
    public Room(Long roomId, String name, String count, String checkIn, String checkOut) {
        this.roomId = roomId;
        this.name = name;
        this.count = count;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

}
