package com.mhkim.tms.batch;

import com.mhkim.tms.v1.travelinfo.controller.dto.RoomItemDto;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class CsvItemReader {

    @Bean
    public FlatFileItemReader<RoomItemDto> reader() {

        FlatFileItemReader<RoomItemDto> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new ClassPathResource("/data/opendata/roominfo.csv"));
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setEncoding("utf-8");

        DefaultLineMapper<RoomItemDto> defaultLineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames("name", "count", "checkIn", "checkOut");
        delimitedLineTokenizer.setStrict(false);

        BeanWrapperFieldSetMapper<RoomItemDto> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(RoomItemDto.class);

        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        flatFileItemReader.setLineMapper(defaultLineMapper);
        return flatFileItemReader;
    }

}
