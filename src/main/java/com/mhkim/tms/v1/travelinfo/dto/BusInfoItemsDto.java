package com.mhkim.tms.v1.travelinfo.dto;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
public class BusInfoItemsDto {

    private List<BusInfoItemDto> busInfoItems;
    private int numOfRows;
    private int pageNo;
    private int totalCount;

    public BusInfoItemsDto(String jsonData) {
        log.debug("# {}", jsonData);
        JsonObject jsonObject = new Gson().fromJson(jsonData, JsonObject.class);
        JsonObject bodyObject = jsonObject.get("response").getAsJsonObject().get("body").getAsJsonObject();

        String itemJson = bodyObject.get("items").getAsJsonObject().get("item").toString();
        this.busInfoItems = new Gson().fromJson(itemJson, new TypeToken<List<BusInfoItemDto>>() {}.getType());

        this.numOfRows = bodyObject.get("numOfRows").getAsInt();
        this.pageNo = bodyObject.get("pageNo").getAsInt();
        this.totalCount = bodyObject.get("totalCount").getAsInt();
    }

}
