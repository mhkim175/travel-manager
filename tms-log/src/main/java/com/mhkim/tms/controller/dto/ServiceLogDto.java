package com.mhkim.tms.controller.dto;

import com.mhkim.tms.model.ServiceLog;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

public class ServiceLogDto {

    @EqualsAndHashCode(callSuper = false)
    @Relation(itemRelation = "qna", collectionRelation = "qnas")
    @Getter
    @Setter
    @ToString
    public static class Response extends RepresentationModel<Response> {

        @ApiModelProperty(value = "로그 ID")
        private String serviceLogId;

        @ApiModelProperty(value = "서비스구분")
        private String type;

        @ApiModelProperty(value = "로그 데이터")
        private String logData;

        @ApiModelProperty(value = "로그 날짜")
        private String logDate;

        public Response(ServiceLog serviceLog) {
            this.serviceLogId = serviceLog.getServiceLogId();
            this.type = serviceLog.getType();
            this.logData = serviceLog.getLogData();
            this.logDate = serviceLog.getLogDate();
        }

    }

    @Getter
    @Setter
    @ToString
    public static class Add {

        @ApiModelProperty(value = "서비스구분", required = true)
        private String type;

        @ApiModelProperty(value = "로그 데이터", required = true)
        private String logData;

        @ApiModelProperty(value = "로그 날짜", required = true)
        private String logDate;

        public ServiceLog toEntity() {
            return ServiceLog.builder()
                    .type(type)
                    .logData(logData)
                    .logDate(logDate)
                    .build();
        }

    }

}
