package com.mhkim.tms.v1.travelinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhkim.tms.v1.travelinfo.entity.FlightInfo;

@Repository
public interface FlightInfoRepository extends JpaRepository<FlightInfo, Long> {
}
