package com.mhkim.tms.entity.bus;

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

@DynamicUpdate
@Table(uniqueConstraints = {@UniqueConstraint(name = "uk_bus_idx_book_date", columnNames = {"bus_idx", "book_date"})})
@Entity
@Getter
@NoArgsConstructor
@ToString
public class BusBooking extends BaseTimeEntity {

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
