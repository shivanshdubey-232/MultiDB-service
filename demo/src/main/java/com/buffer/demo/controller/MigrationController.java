package com.buffer.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buffer.demo.service.MigrationService;

@RestController
public class MigrationController {

    @Autowired
    private MigrationService migrationService;

    @PostMapping("/migrate")
    public String migrateData() {
        migrationService.migrateData();
        return "Migration completed successfully";
    }
}
