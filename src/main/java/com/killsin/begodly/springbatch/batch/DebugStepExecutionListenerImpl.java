package com.killsin.begodly.springbatch.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

@Slf4j
public class DebugStepExecutionListenerImpl implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("Before Step | StepName: {} | Status: {}", stepExecution.getStepName(), stepExecution.getStatus());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("After Step | StepName: {} | ExitStatus: {}", stepExecution.getStepName(), stepExecution.getExitStatus());
        return stepExecution.getExitStatus();
    }
}
