package com.mhkim.tms.controller.v1.bus.dto;

import com.mhkim.tms.entity.bus.Bus;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

public class BusDto {

    @EqualsAndHashCode(callSuper = false)
    @Relation(itemRelation = "bus", collectionRelation = "buses")
    @Getter
    @Setter
    @ToString
    public static class Response extends RepresentationModel<Response> {

        @ApiModelProperty(value = "버스 ID")
        private Long busIdx;

        @ApiModelProperty(value = "경로 ID")
        private String routeId;

        @ApiModelProperty(value = "등급")
        private String gradeNm;

        @ApiModelProperty(value = "도착시간")
        private String depPlandTime;

        @ApiModelProperty(value = "출발시간")
        private String arrPlandTime;

        @ApiModelProperty(value = "도착지")
        private String depPlaceNm;

        @ApiModelProperty(value = "출발지")
        private String arrPlaceNm;

        @ApiModelProperty(value = "요금")
        private String charge;

        public Response(Bus bus) {
            this.busIdx = bus.getBusIdx();
            this.routeId = bus.getRouteId();
            this.gradeNm = bus.getGradeNm();
            this.depPlandTime = bus.getDepPlandTime();
            this.arrPlandTime = bus.getArrPlandTime();
            this.depPlaceNm = bus.getDepPlaceNm();
            this.arrPlaceNm = bus.getArrPlaceNm();
            this.charge = bus.getCharge();
        }

    }

    @Getter
    @Setter
    @ToString
    public static class Request {

        @ApiModelProperty(value = "경로 ID", required = true)
        private String routeId;

        @ApiModelProperty(value = "등급", required = true)
        private String gradeNm;

        @ApiModelProperty(value = "도착시간", required = true)
        private String depPlandTime;

        @ApiModelProperty(value = "출발시간", required = true)
        private String arrPlandTime;

        @ApiModelProperty(value = "도착지", required = true)
        private String depPlaceNm;

        @ApiModelProperty(value = "출발지", required = true)
        private String arrPlaceNm;

        @ApiModelProperty(value = "요금", required = true)
        private String charge;

        public Bus toEntity() {
            return Bus.builder()
                    .routeId(routeId)
                    .gradeNm(gradeNm)
                    .depPlandTime(depPlandTime)
                    .arrPlandTime(arrPlandTime)
                    .depPlaceNm(depPlaceNm)
                    .arrPlaceNm(arrPlaceNm)
                    .charge(charge)
                    .build();
        }

    }

}
