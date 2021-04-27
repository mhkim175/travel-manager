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
@Table(name = "roominfo")
public class RoomInfo extends BaseTime {

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

    @Builder
    public RoomInfo(Long roominfoId, String name, String count, String checkIn, String checkOut) {
        this.roominfoId = roominfoId;
        this.name = name;
        this.count = count;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

}
