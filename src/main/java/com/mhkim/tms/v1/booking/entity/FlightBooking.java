package com.mhkim.tms.v1.booking.entity;

import com.mhkim.tms.common.BaseTime;
import com.mhkim.tms.v1.travelinfo.entity.Flight;
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
@Table(uniqueConstraints = {@UniqueConstraint(name = "uk_room_id_book_date", columnNames = {"book_date", "room_id"})})
public class FlightBooking extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightBookId;

    @Column(name = "book_date", nullable = false)
    private LocalDate bookDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Flight flight;

    @Builder
    public FlightBooking(LocalDate bookDate, Long flightBookId, Flight flight, User user) {

        checkNotNull(bookDate, "BookDate must be provided.");
        checkNotNull(flight, "Flight must be provided.");
        checkNotNull(user, "User must be provided.");

        this.flightBookId = flightBookId;
        this.bookDate = bookDate;
        this.flight = flight;
        this.user = user;
    }

}
