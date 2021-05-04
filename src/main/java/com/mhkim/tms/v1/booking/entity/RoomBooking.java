package com.mhkim.tms.v1.booking.entity;

import com.mhkim.tms.common.BaseTime;
import com.mhkim.tms.v1.travelinfo.entity.room.Room;
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
@Table(uniqueConstraints = {@UniqueConstraint(name = "uk_room_idx_book_date", columnNames = {"room_idx", "book_date"})})
public class RoomBooking extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomBookIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_idx")
    private Room room;

    @Column(name = "book_date", nullable = false)
    private LocalDate bookDate;

    @Builder
    public RoomBooking(Long roomBookIdx, Room room, User user, LocalDate bookDate) {

        checkNotNull(room, "Room must be provided.");
        checkNotNull(user, "User must be provided.");
        checkNotNull(bookDate, "BookDate must be provided.");

        this.roomBookIdx = roomBookIdx;
        this.room = room;
        this.user = user;
        this.bookDate = bookDate;
    }

}
