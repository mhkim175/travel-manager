package com.mhkim.tms.batch;

import com.mhkim.tms.v1.travelinfo.controller.dto.RoomItemDto;
import com.mhkim.tms.v1.travelinfo.entity.Room;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
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
        return jobBuilderFactory.get("csvReaderJob")
                .start(csvItemReaderStep(null))
                .build();
    }

    @Bean
    @JobScope
    public Step csvItemReaderStep(@Value("#{jobParameters[runJobDate]}") String runJobDate) {
        log.info("#RunJob Date: {}", runJobDate);
        return stepBuilderFactory.get("csvReaderJob-csvItemReadWriteStep")
                .<RoomItemDto, Room>chunk(CHUNK_SIZE)
                .reader(csvItemReader.reader())
                .processor(csvItemProcessor)
                .writer(csvItemWriter)
                .build();
    }

}
