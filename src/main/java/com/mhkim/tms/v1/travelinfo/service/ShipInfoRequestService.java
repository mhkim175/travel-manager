package com.mhkim.tms.v1.travelinfo.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import com.mhkim.tms.config.OpendataProp;
import com.mhkim.tms.v1.travelinfo.controller.dto.ShipInfoItemsDto;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class ShipInfoRequestService {

    private final WebClient tagoWebClient;
    private final OpendataProp opendataProp;

    public Mono<ShipInfoItemsDto> requestShipInfo(int pageNo) {
        MultiValueMap<String, String> queryParams = getQueryParams(pageNo);
        
        return tagoWebClient.get()
                .uri(uriBuilder -> uriBuilder.path(opendataProp.getShipServiceUrl())
                        .queryParams(queryParams)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(String.class)
                .flatMap(data -> Mono.just(new ShipInfoItemsDto(data)));
    }

    private MultiValueMap<String, String> getQueryParams(int pageNo) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();

        queryParams.add("serviceKey", opendataProp.getServiceKey());
        queryParams.add("numOfRows", opendataProp.getNumOfRows());
        queryParams.add("pageNo", String.valueOf(pageNo));
        queryParams.add("depPlandTime", "20201201");
        queryParams.add("depNodeId", "SEA96140");

        return queryParams;
    }

}
