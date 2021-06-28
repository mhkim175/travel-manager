package com.mhkim.tms.repository.ship;

import com.mhkim.tms.entity.bus.BusBooking;
import com.mhkim.tms.entity.ship.Ship;
import com.mhkim.tms.entity.ship.ShipBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipBookingRepository extends JpaRepository<ShipBooking, Long> {

    public List<ShipBooking> findAllByUserUserIdx(Long userIdx);

}
