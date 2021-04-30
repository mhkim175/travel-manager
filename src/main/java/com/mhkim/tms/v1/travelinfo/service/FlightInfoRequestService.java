package com.mhkim.tms.v1.travelinfo.service;

import com.mhkim.tms.config.OpendataProperty;
import com.mhkim.tms.v1.travelinfo.controller.dto.FlightItemsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class FlightInfoRequestService {

    private final WebClient tagoWebClient;
    private final OpendataProperty opendataProperty;

    public Mono<FlightItemsDto> requestFlightInfo(int pageNo) {
        MultiValueMap<String, String> queryParams = getQueryParams(pageNo);

        return tagoWebClient.get()
                .uri(uriBuilder -> uriBuilder.path(opendataProperty.getFlightServiceUrl())
                        .queryParams(queryParams)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(String.class)
                .flatMap(data -> Mono.just(new FlightItemsDto(data)));
    }

    private MultiValueMap<String, String> getQueryParams(int pageNo) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();

        queryParams.add("serviceKey", opendataProperty.getServiceKey());
        queryParams.add("numOfRows", opendataProperty.getNumOfRows());
        queryParams.add("pageNo", String.valueOf(pageNo));
        queryParams.add("depAirportId", "NAARKPC");
        queryParams.add("arrAirportId", "NAARKSS");
        queryParams.add("depPlandTime", "20210521");

        return queryParams;
    }

}
