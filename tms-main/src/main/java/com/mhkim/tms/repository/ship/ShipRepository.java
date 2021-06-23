package com.mhkim.tms.repository.ship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhkim.tms.entity.ship.Ship;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
}
