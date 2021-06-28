package com.mhkim.tms.repository;

import com.mhkim.tms.model.ServiceLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ServiceLogRepository extends ElasticsearchRepository<ServiceLog, String> {

    List<ServiceLog> findAll();

}
