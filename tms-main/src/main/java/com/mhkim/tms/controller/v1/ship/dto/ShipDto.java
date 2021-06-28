package com.mhkim.tms.controller.v1.ship.dto;

import com.mhkim.tms.entity.ship.Ship;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

public class ShipDto {

    @EqualsAndHashCode(callSuper = false)
    @Relation(itemRelation = "ship", collectionRelation = "ships")
    @Getter
    @Setter
    @ToString
    public static class Response extends RepresentationModel<Response> {

        @ApiModelProperty(value = "선박 ID")
        private Long shipIdx;

        @ApiModelProperty(value = "선박명")
        private String vihicleNm;

        @ApiModelProperty(value = "도착지")
        private String depPlaceNm;

        @ApiModelProperty(value = "출발지")
        private String arrPlaceNm;

        @ApiModelProperty(value = "도착시간")
        private String depPlandTime;

        @ApiModelProperty(value = "출발시간")
        private String arrPlandTime;

        @ApiModelProperty(value = "요금")
        private String charge;

        public Response(Ship ship) {
            this.shipIdx = ship.getShipIdx();
            this.vihicleNm = ship.getVihicleNm();
            this.depPlaceNm = ship.getDepPlaceNm();
            this.arrPlaceNm = ship.getArrPlaceNm();
            this.depPlandTime = ship.getDepPlandTime();
            this.arrPlandTime = ship.getArrPlandTime();
            this.charge = ship.getCharge();
        }

    }

    @Getter
    @Setter
    @ToString
    public static class Request {

        @ApiModelProperty(value = "선박명")
        private String vihicleNm;

        @ApiModelProperty(value = "도착지")
        private String depPlaceNm;

        @ApiModelProperty(value = "출발지")
        private String arrPlaceNm;

        @ApiModelProperty(value = "도착시간")
        private String depPlandTime;

        @ApiModelProperty(value = "출발시간")
        private String arrPlandTime;

        @ApiModelProperty(value = "요금")
        private String charge;

        public Ship toEntity() {
            return Ship.builder()
                    .vihicleNm(vihicleNm)
                    .depPlaceNm(depPlaceNm)
                    .arrPlaceNm(arrPlaceNm)
                    .depPlandTime(depPlandTime)
                    .arrPlandTime(arrPlandTime)
                    .charge(charge)
                    .build();
        }

    }

}
