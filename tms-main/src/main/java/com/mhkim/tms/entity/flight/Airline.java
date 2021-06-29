package com.mhkim.tms.entity.flight;

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
public class Airline extends BaseTimeEntity {

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
