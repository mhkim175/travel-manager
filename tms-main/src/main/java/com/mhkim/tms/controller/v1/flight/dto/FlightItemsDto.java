package com.mhkim.tms.controller.v1.flight.dto;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class FlightItemsDto {

    private List<FlightItemDto> flightItems;
    private int numOfRows;
    private int pageNo;
    private int totalCount;

    public FlightItemsDto(String jsonData) {
        JsonObject jsonObject = new Gson().fromJson(jsonData, JsonObject.class);
        JsonObject bodyObject = jsonObject.get("response").getAsJsonObject().get("body").getAsJsonObject();

        String itemJson = bodyObject.get("items").getAsJsonObject().get("item").toString();
        this.flightItems = new Gson().fromJson(itemJson, new TypeToken<List<FlightItemDto>>() {
        }.getType());

        this.numOfRows = bodyObject.get("numOfRows").getAsInt();
        this.pageNo = bodyObject.get("pageNo").getAsInt();
        this.totalCount = bodyObject.get("totalCount").getAsInt();
    }

}
