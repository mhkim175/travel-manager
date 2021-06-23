package com.mhkim.tms.schedule;

import java.time.LocalDateTime;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BatchScheduler {

    private final Job job;
    private final JobLauncher jobLauncher;

    @Scheduled(cron="0 0 05 * * ?")
    //@Scheduled(fixedDelay = 30000)
    public void runJob() throws JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException, JobParametersInvalidException {
        jobLauncher.run(job, new JobParametersBuilder().addString("runJobDate", LocalDateTime.now().toString()).toJobParameters());
    }

}
