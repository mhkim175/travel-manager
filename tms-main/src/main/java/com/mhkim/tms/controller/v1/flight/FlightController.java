package com.mhkim.tms.controller.v1.flight;

import com.mhkim.tms.service.bus.BusService;
import com.mhkim.tms.service.flight.FlightService;
import com.mhkim.tms.service.ship.ShipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {

    private final FlightService flightService;
    @PostMapping(value = "/sync")
    public void syncFlight() {
        flightService.syncFlight();
    }

}
