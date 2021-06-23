package com.mhkim.tms.entity.ship;

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
@Table(uniqueConstraints = {@UniqueConstraint(name = "uk_ship_idx_book_date", columnNames = {"ship_idx", "book_date"})})
public class ShipBooking extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipBookIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ship_idx")
    private Ship ship;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @Column(name = "book_date", nullable = false)
    private LocalDate bookDate;

    @Builder
    public ShipBooking(Long shipBookIdx, Ship ship, User user, LocalDate bookDate) {

        checkNotNull(ship, "Ship must be provided.");
        checkNotNull(user, "User must be provided.");
        checkNotNull(bookDate, "BookDate must be provided.");

        this.shipBookIdx = shipBookIdx;
        this.ship = ship;
        this.user = user;
        this.bookDate = bookDate;
    }

}
