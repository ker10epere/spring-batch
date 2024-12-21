package com.killsin.begodly.springbatch.batch;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JobRunner implements ApplicationListener<ApplicationReadyEvent> {
    private final Job job;
    private final JobRepository jr;

    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
        jobLauncher.setJobRepository(jr);
        jobLauncher.afterPropertiesSet();
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("kia", "Kia")
                .toJobParameters();
        jobLauncher.run(job, jobParameters);
    }
}
