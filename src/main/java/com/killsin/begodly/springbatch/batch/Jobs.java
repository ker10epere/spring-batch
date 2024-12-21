package com.killsin.begodly.springbatch.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@Slf4j
@Component
public class Jobs {

    @StepScope
    @Bean
    ItemReader<String> reader(@Value("#{jobParameters}") Map<String, Object> jobParameters) {
        return new IteratorItemReader<>(List.of("Kim", "Ker", (String) jobParameters.get("kia")));
    }

    @Bean
    public Job simpleJob(JobRepository jr, PlatformTransactionManager tx, ItemReader<String> reader) throws InterruptedException {
        return new JobBuilder("SimpleJob-" + UUID.randomUUID(), jr)
                .start(new StepBuilder("step", jr)
                        .chunk(2, tx)
                        .reader(reader)
                        .writer(value -> log.info("* chunks: {}", value.getItems()))
                        .listener(new DebugStepExecutionListenerImpl())
                        .build())
                .build();
    }
}
