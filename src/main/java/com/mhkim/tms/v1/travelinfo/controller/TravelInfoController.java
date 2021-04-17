package com.mhkim.tms.v1.travelinfo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhkim.tms.v1.travelinfo.service.FlightInfoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/travelinfo")
public class TravelInfoController {

    private final FlightInfoService flightService;

    @PostMapping(value = "/flightinfo/sync")
    public void syncFlightInfo() {
        flightService.syncFlightInfo();
    }

}
