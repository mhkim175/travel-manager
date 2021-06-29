package com.mhkim.tms.repository.ship;

import com.mhkim.tms.entity.ship.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {

    Optional<Ship> findByVihicleNm(String vihicleNm);

}
