package com.templatetasks.java.quarkus.scheduled;

import io.quarkus.scheduler.Scheduled;
import io.quarkus.scheduler.ScheduledExecution;
import lombok.extern.slf4j.Slf4j;

import jakarta.enterprise.context.ApplicationScoped;

/**
 * @author Vadim Starichkov (starichkovva@gmail.com)
 * @since 06.08.2021 13:36
 */
@ApplicationScoped
@Slf4j
public class ScheduledTasksService {

    @Scheduled(every = "45s", delayed = "15s")
    void every60s() {
        logger.info("Cron job 'Every 45 seconds' executed!");
    }

    @Scheduled(cron = "{scheduled.job1.cron}")
    void cronJobWithExpressionInConfig(ScheduledExecution execution) {
        logger.info("Cron job 'Expression configured in application.yaml' executed at {}!", execution.getScheduledFireTime());
    }

    @Scheduled(cron = "{scheduled.job2.cron}")
    void disabledCronJob() {
        throw new IllegalStateException("Should never be executed!");
    }
}
