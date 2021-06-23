package com.mhkim.tms.controller.v1.bus;

import com.mhkim.tms.service.bus.BusService;
import com.mhkim.tms.service.flight.FlightService;
import com.mhkim.tms.service.ship.ShipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/bus")
public class TravelInfoController {

    private final BusService busService;

    @PostMapping(value = "/bus/sync")
    public void syncBus() {
        busService.syncBus();
    }

}
