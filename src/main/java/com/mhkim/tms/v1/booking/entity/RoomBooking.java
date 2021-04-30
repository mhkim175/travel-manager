package com.mhkim.tms.v1.booking.entity;

import com.mhkim.tms.common.BaseTime;
import com.mhkim.tms.v1.travelinfo.entity.Room;
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
@Table(uniqueConstraints = {@UniqueConstraint(name = "uk_book_date_room_id", columnNames = {"book_date", "room_id"})})
public class RoomBooking extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomBookId;

    @Column(name = "book_date", nullable = false)
    private LocalDate bookDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Builder
    public RoomBooking(LocalDate bookDate, Long roomBookId, Room room, User user) {

        checkNotNull(bookDate, "bookDate must be provided.");
        checkNotNull(room, "room must be provided.");
        checkNotNull(user, "user must be provided.");

        this.roomBookId = roomBookId;
        this.bookDate = bookDate;
        this.room = room;
        this.user = user;
    }

}
