package com.mhkim.tms.v1.travelinfo.entity.flight;

import com.mhkim.tms.common.BaseTime;
import com.mhkim.tms.v1.user.entity.User;
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
public class FlightSeat extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightSeatIdx;

    @Column(length = 100)
    private String seatName;

    @Column(length = 30)
    private String classCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_idx")
    private Flight flight;

    @Builder
    public FlightSeat(Long flightSeatIdx, String seatName, String classCode, Flight flight) {
        this.flightSeatIdx = flightSeatIdx;
        this.seatName = seatName;
        this.classCode = classCode;
        this.flight = flight;
    }

}
