package com.mhkim.tms.v1.booking.entity;

import com.mhkim.tms.common.BaseTime;
import com.mhkim.tms.v1.travelinfo.entity.bus.Bus;
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
@Table(uniqueConstraints = {@UniqueConstraint(name = "uk_bus_idx_book_date", columnNames = {"bus_idx", "book_date"})})
public class BusBooking extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busBookIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_idx")
    private Bus bus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @Column(name = "book_date", nullable = false)
    private LocalDate bookDate;

    @Builder
    public BusBooking(Long busBookIdx, Bus bus, User user, LocalDate bookDate) {

        checkNotNull(bus, "Bus must be provided.");
        checkNotNull(user, "User must be provided.");
        checkNotNull(bookDate, "BookDate must be provided.");

        this.busBookIdx = busBookIdx;
        this.bus = bus;
        this.user = user;
        this.bookDate = bookDate;
    }

}
