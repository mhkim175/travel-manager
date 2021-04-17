package com.mhkim.tms.batch;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.mhkim.tms.v1.travelinfo.dto.RoomInfoDto;

@Configuration
public class CsvItemReader {

    @Bean
    public FlatFileItemReader<RoomInfoDto> reader() {

        FlatFileItemReader<RoomInfoDto> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new ClassPathResource("/data/opendata/roominfo.csv"));
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setEncoding("utf-8");

        DefaultLineMapper<RoomInfoDto> defaultLineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames("name", "count", "checkIn", "checkOut");
        delimitedLineTokenizer.setStrict(false);

        BeanWrapperFieldSetMapper<RoomInfoDto> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(RoomInfoDto.class);

        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        flatFileItemReader.setLineMapper(defaultLineMapper);
        return flatFileItemReader;
    }

}
