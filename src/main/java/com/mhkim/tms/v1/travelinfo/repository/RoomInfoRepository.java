package com.mhkim.tms.v1.travelinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhkim.tms.v1.travelinfo.entity.RoomInfo;

@Repository
public interface RoomInfoRepository extends JpaRepository<RoomInfo, Long> {
}
