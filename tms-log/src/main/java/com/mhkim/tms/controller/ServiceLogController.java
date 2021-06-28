package com.mhkim.tms.controller;

import com.mhkim.tms.controller.dto.ServiceLogDto;
import com.mhkim.tms.model.ServiceLog;
import com.mhkim.tms.repository.ServiceLogRepository;
import com.mhkim.tms.service.ServiceLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Api(tags = {"ServiceLog"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/logs")
public class ServiceLogController {

    private final ServiceLogService serviceLogService;

    @ApiOperation(value = "서비스로그 전체 조회")
    @GetMapping
    public ResponseEntity<List<ServiceLog>> getServiceLogs() {
        return ResponseEntity.ok(serviceLogService.getServiceLogs().stream().collect(toList()));
    }

    @ApiOperation(value = "서비스로그 추가")
    @PostMapping
    public ResponseEntity<ServiceLog> addServiceLog(@RequestBody ServiceLogDto.Add param) {
        return ResponseEntity.ok(serviceLogService.addServiceLogIndex(param.toEntity()));
    }

}
