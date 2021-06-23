package com.mhkim.tms.repository.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhkim.tms.entity.room.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
