package com.mhkim.tms.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mhkim.tms.v1.travelinfo.dto.RoomInfoDto;
import com.mhkim.tms.v1.travelinfo.entity.RoomInfo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class CsvReaderJob {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CsvItemReader csvItemReader;
    private final CsvItemProcessor csvItemProcessor;
    private final CsvItemWriter csvItemWriter;

    private static final int CHUNK_SIZE = 100;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("csvReaderJob").start(csvItemReaderStep()).build();
    }

    @Bean
    public Step csvItemReaderStep() {
        return stepBuilderFactory.get("csvReaderJob-csvItemReadWriteStep")
                .<RoomInfoDto, RoomInfo>chunk(CHUNK_SIZE)
                .reader(csvItemReader.reader())
                .processor(csvItemProcessor)
                .writer(csvItemWriter)
                .build();
    }

}
