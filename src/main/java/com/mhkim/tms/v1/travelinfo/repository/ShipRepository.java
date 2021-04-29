package com.mhkim.tms.v1.travelinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhkim.tms.v1.travelinfo.entity.Ship;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
}
