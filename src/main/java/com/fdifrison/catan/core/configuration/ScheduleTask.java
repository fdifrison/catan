package com.fdifrison.catan.core.configuration;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTask {

    private static final Logger log = LoggerFactory.getLogger(ScheduleTask.class);
    private final DataSource dataSource;

    public ScheduleTask(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Scheduled(fixedRate = 10000) // ping every 10sec to keep render pod alive
    public void pingDb() throws SQLException {
        log.info("Ping DB");
        dataSource.getConnection().isValid(1);
    }
}
