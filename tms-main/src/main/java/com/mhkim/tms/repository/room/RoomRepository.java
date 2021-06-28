package com.mhkim.tms.repository.room;

import com.mhkim.tms.entity.room.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhkim.tms.entity.room.Room;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    public Optional<Room> findByName(String name);

}
