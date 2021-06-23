package com.mhkim.tms.entity.flight;

import com.mhkim.tms.entity.BaseTimeEntity;
import com.mhkim.tms.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
@NoArgsConstructor
@ToString
@DynamicUpdate
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "uk_flight_seat_idx_book_date", columnNames = {"flight_seat_idx", "book_date"})})
public class FlightBooking extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightBookIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_seat_idx")
    private FlightSeat flightSeat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @Column(name = "book_date", nullable = false)
    private LocalDate bookDate;

    @Builder
    public FlightBooking(Long flightBookIdx, FlightSeat flightSeat, User user, LocalDate bookDate) {

        checkNotNull(flightSeat, "FlightSeat must be provided.");
        checkNotNull(user, "User must be provided.");
        checkNotNull(bookDate, "BookDate must be provided.");

        this.flightBookIdx = flightBookIdx;
        this.flightSeat = flightSeat;
        this.user = user;
        this.bookDate = bookDate;
    }

}
