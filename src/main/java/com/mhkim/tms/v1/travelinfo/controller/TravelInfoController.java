package com.mhkim.tms.v1.travelinfo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhkim.tms.v1.travelinfo.service.BusInfoService;
import com.mhkim.tms.v1.travelinfo.service.FlightInfoService;
import com.mhkim.tms.v1.travelinfo.service.ShipInfoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/travelinfo")
public class TravelInfoController {

    private final BusInfoService busInfoService;
    private final FlightInfoService flightService;
    private final ShipInfoService shipInfoService;

    @PostMapping(value = "/businfo/sync")
    public void syncBusInfo() {
        busInfoService.syncBusInfo();
    }

    @PostMapping(value = "/flightinfo/sync")
    public void syncFlightInfo() {
        flightService.syncFlightInfo();
    }

    @PostMapping(value = "/shipinfo/sync")
    public void syncShipInfo() {
        shipInfoService.syncShipInfo();
    }

}
