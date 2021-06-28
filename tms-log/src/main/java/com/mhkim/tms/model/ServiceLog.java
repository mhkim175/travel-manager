package com.mhkim.tms.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Data
@Document(indexName = "service-log")
@Setting(settingPath = "elastic-setting.json")
public class ServiceLog {


    /* 로그  */
    @Id
    @Field(type = FieldType.Long)
    private String serviceLogId;

    /* 서비스구분 */
    @Field(type = FieldType.Keyword)
    private String type;

    /* 로그 데이터 */
    @Field(type = FieldType.Text)
    private String logData;

    /* 로그 날짜 */
    @Field(type = FieldType.Date)
    private String logDate;

    @Builder
    public ServiceLog(String serviceLogId, String type, String logData, String logDate) {
        checkArgument(isNotEmpty(type), "type must be provided.");
        checkArgument(isNotEmpty(logData), "logData must be provided.");
        checkArgument(isNotEmpty(logDate), "logDate must be provided.");

        this.serviceLogId = serviceLogId;
        this.type = type;
        this.logData = logData;
        this.logDate = logDate;
    }
}
