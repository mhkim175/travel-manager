package com.mhkim.tms.service.ship;

import com.mhkim.tms.config.OpendataProperty;
import com.mhkim.tms.controller.v1.ship.dto.ShipItemsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class ShipInfoRequestService {

    private final WebClient tagoWebClient;
    private final OpendataProperty opendataProperty;

    public Mono<ShipItemsDto> requestShipInfo(int pageNo) {
        MultiValueMap<String, String> queryParams = getQueryParams(pageNo);

        return tagoWebClient.get()
                .uri(uriBuilder -> uriBuilder.path(opendataProperty.getShipServiceUrl())
                        .queryParams(queryParams)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(String.class)
                .flatMap(data -> Mono.just(new ShipItemsDto(data)));
    }

    private MultiValueMap<String, String> getQueryParams(int pageNo) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();

        queryParams.add("serviceKey", opendataProperty.getServiceKey());
        queryParams.add("numOfRows", opendataProperty.getNumOfRows());
        queryParams.add("pageNo", String.valueOf(pageNo));
        queryParams.add("depPlandTime", "20201201");
        queryParams.add("depNodeId", "SEA96140");

        return queryParams;
    }

}
