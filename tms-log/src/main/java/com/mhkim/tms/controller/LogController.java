package com.mhkim.tms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/logs")
public class LogController {

    @GetMapping
    public ResponseEntity<String> getLogs() {
        return ResponseEntity.ok("로그정보 전체 조회");
    }

}
