package com.mhkim.tms.v1.travelinfo.service;

import reactor.core.publisher.Mono;

public interface TravelInfoRequestService {
    public Mono<?> requestTravelInfo(int pageNo);
}
