package com.mhkim.tms.controller.v1.bus.dto;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BusItemsDto {

    private List<BusItemDto> busItems;
    private int numOfRows;
    private int pageNo;
    private int totalCount;

    public BusItemsDto(String jsonData) {
        JsonObject jsonObject = new Gson().fromJson(jsonData, JsonObject.class);
        JsonObject bodyObject = jsonObject.get("response").getAsJsonObject().get("body").getAsJsonObject();

        String itemJson = bodyObject.get("items").getAsJsonObject().get("item").toString();
        this.busItems = new Gson().fromJson(itemJson, new TypeToken<List<BusItemDto>>() {}.getType());

        this.numOfRows = bodyObject.get("numOfRows").getAsInt();
        this.pageNo = bodyObject.get("pageNo").getAsInt();
        this.totalCount = bodyObject.get("totalCount").getAsInt();
    }

}
