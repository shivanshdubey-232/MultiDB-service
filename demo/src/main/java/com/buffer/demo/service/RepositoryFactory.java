package com.buffer.demo.service;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.buffer.demo.entity.Department;
import com.buffer.demo.entity.sql.DepartmentSql;
import com.buffer.demo.repository.DepartmentRepository;
import com.buffer.demo.repository.DepartmentRepositorySql;

@Service
public class RepositoryFactory {

    private final ApplicationContext applicationContext;

    public RepositoryFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public <T> Object getRepository(Class<T> entityType) {
        if (entityType.equals(DepartmentSql.class)) {
            return applicationContext.getBean(DepartmentRepositorySql.class);
        } else if (entityType.equals(Department.class)) {
            return applicationContext.getBean(DepartmentRepository.class);
        } else {
            throw new RuntimeException("No repository found for entity type: " + entityType.getSimpleName());
        }
    }
}
