package com.buffer.demo.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.buffer.demo.entity.Department;
import com.buffer.demo.entity.sql.DepartmentSql;
import com.buffer.demo.errors.DepartmentNotFoundException;
import com.buffer.demo.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.classic.Logger;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ObjectMapper objectMapper;

    private final Logger LOGGER = (Logger) LoggerFactory.getLogger(DepartmentController.class);

    @PostMapping("/department/{type}")
    public Object saveDepartment(@PathVariable("type") String type, @RequestBody Object department) {
        LOGGER.info("Inside saveDepartment of DepartmentController");
        Class<?> entityType = getEntityType(type);
        Object entity = objectMapper.convertValue(department, entityType);
        return departmentService.saveDepartment(entity);
    }

    @GetMapping("/departments/{type}")
    public List<?> fetchDepartmentList(@PathVariable("type") String type) {
        LOGGER.info("Inside fetchDepartmentList of DepartmentController");
        Class<?> entityType = getEntityType(type);
        return departmentService.fetchDepartmentList(entityType);
    }

    @GetMapping("/department/{type}/{id}")
    public Object fetchDepartmentById(@PathVariable("type") String type, @PathVariable("id") String id) throws DepartmentNotFoundException {
        LOGGER.info("Inside fetchDepartmentById of DepartmentController");
        Class<?> entityType = getEntityType(type);
        return departmentService.fetchDepartmentById(id, entityType);
    }

    @DeleteMapping("/department/{type}/{id}")
    public String deleteDepartmentById(@PathVariable("type") String type, @PathVariable("id") String id) {
        LOGGER.info("Inside deleteDepartmentById of DepartmentController");
        Class<?> entityType = getEntityType(type);
        departmentService.deleteDepartmentById(id, entityType);
        return "Department deleted successfully";
    }

    @PutMapping("/department/{type}/{id}")
    public Object updateDepartment(@PathVariable("type") String type, @PathVariable("id") String id, @RequestBody Object department) throws DepartmentNotFoundException {
        LOGGER.info("Inside updateDepartment of DepartmentController");
        Class<?> entityType = getEntityType(type);
        Object entity = objectMapper.convertValue(department, entityType);
        return departmentService.updateDepartment(id, entity);
    }

    private Class<?> getEntityType(String type) {
        switch (type.toLowerCase()) {
            case "sql":
                return DepartmentSql.class;
            case "mongo":
                return Department.class;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown department type: " + type);
        }
    }
}
