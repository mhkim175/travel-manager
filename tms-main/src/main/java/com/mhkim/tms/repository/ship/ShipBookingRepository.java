package com.mhkim.tms.repository.ship;

import com.mhkim.tms.entity.ship.ShipBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShipBookingRepository extends JpaRepository<ShipBooking, Long> {

    public List<ShipBooking> findAllByUserUserIdx(Long userIdx);

    public Optional<ShipBooking> findByShipShipIdxAndUserUserIdxAndBookDate(Long shipIdx, Long userIdx, LocalDate bookDate);

}
