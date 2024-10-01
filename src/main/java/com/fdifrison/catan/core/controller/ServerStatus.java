package com.fdifrison.catan.core.controller;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("server-status")
public class ServerStatus {

    private final DataSource dataSource;

    public ServerStatus(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping
    public void status() throws SQLException {
        dataSource.getConnection().isValid(1);
    }
}
