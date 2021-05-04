package com.mhkim.tms.v1.booking.entity;

import com.mhkim.tms.common.BaseTime;
import com.mhkim.tms.v1.travelinfo.entity.flight.Flight;
import com.mhkim.tms.v1.user.entity.User;
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
@Table(uniqueConstraints = {@UniqueConstraint(name = "uk_flight_idx_book_date", columnNames = {"flight_idx", "book_date"})})
public class FlightBooking extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightBookIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_idx")
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @Column(name = "book_date", nullable = false)
    private LocalDate bookDate;

    @Builder
    public FlightBooking(Long flightBookIdx, Flight flight, User user, LocalDate bookDate) {

        checkNotNull(flight, "Flight must be provided.");
        checkNotNull(user, "User must be provided.");
        checkNotNull(bookDate, "BookDate must be provided.");

        this.flightBookIdx = flightBookIdx;
        this.flight = flight;
        this.user = user;
        this.bookDate = bookDate;
    }

}
