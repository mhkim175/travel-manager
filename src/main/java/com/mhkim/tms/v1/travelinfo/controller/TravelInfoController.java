package com.mhkim.tms.v1.travelinfo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhkim.tms.v1.travelinfo.service.BusService;
import com.mhkim.tms.v1.travelinfo.service.FlightService;
import com.mhkim.tms.v1.travelinfo.service.ShipService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/travel/info")
public class TravelInfoController {

    private final BusService busService;
    private final FlightService flightService;
    private final ShipService shipService;

    @PostMapping(value = "/bus/sync")
    public void syncBus() {
        busService.syncBus();
    }

    @PostMapping(value = "/flight/sync")
    public void syncFlight() {
        flightService.syncFlight();
    }

    @PostMapping(value = "/ship/sync")
    public void syncShip() {
        shipService.syncShip();
    }

}
