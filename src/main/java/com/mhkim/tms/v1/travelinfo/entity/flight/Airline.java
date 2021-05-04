package com.mhkim.tms.v1.travelinfo.entity.flight;

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
public class Airline extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airlineIdx;

    @Column(length = 30)
    private String airlineId;

    @Column(length = 100)
    private String airlineNm;

    @Builder
    public Airline(Long airlineIdx, String airlineId, String airlineNm) {
        this.airlineIdx = airlineIdx;
        this.airlineId = airlineId;
        this.airlineNm = airlineNm;
    }

}
