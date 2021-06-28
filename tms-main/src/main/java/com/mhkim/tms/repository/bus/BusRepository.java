package com.mhkim.tms.repository.bus;

import com.mhkim.tms.entity.ship.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhkim.tms.entity.bus.Bus;

import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

    Optional<Bus> findByRouteId(String routeId);

}
