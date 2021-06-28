package com.mhkim.tms.service;

import com.mhkim.tms.model.ServiceLog;
import com.mhkim.tms.repository.ServiceLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ServiceLogService {

    private final ServiceLogRepository serviceLogRepository;

    public List<ServiceLog> getServiceLogs() {
        return serviceLogRepository.findAll();
    }

    public List<ServiceLog> addServiceLogIndexBulk(List<ServiceLog> serviceLogs) {
        serviceLogRepository.saveAll(serviceLogs);
        return serviceLogs;
    }

    public ServiceLog addServiceLogIndex(ServiceLog serviceLog) {
        serviceLogRepository.save(serviceLog);
        return serviceLog;
    }

}
