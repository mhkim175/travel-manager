package com.mhkim.tms.repository.bus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhkim.tms.entity.bus.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
}
