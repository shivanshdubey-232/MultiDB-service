package com.buffer.demo.service;

import java.util.List;

import com.buffer.demo.errors.DepartmentNotFoundException;

public interface DepartmentService {
    <T> T saveDepartment(T department);
    <T> List<T> fetchDepartmentList(Class<T> entityType);
    <T> T fetchDepartmentById(String id, Class<T> entityType) throws DepartmentNotFoundException;
    void deleteDepartmentById(String id, Class<?> entityType);
    <T> T updateDepartment(String id, T department);
}
