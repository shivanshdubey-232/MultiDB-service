package com.buffer.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.buffer.demo.errors.DepartmentNotFoundException;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final RepositoryFactory repositoryFactory;

    public DepartmentServiceImpl(RepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    @Override
    public <T> T saveDepartment(T department) {
        @SuppressWarnings("unchecked")
        CrudRepository<T, String> repository = (CrudRepository<T, String>) repositoryFactory.getRepository(department.getClass());
        return repository.save(department);
    }

    @Override
    public <T> List<T> fetchDepartmentList(Class<T> entityType) {
        @SuppressWarnings("unchecked")
        CrudRepository<T, String> repository = (CrudRepository<T, String>) repositoryFactory.getRepository(entityType);
        return (List<T>) repository.findAll();
    }

    @Override
    public <T> T fetchDepartmentById(String id, Class<T> entityType) throws DepartmentNotFoundException {
        @SuppressWarnings("unchecked")
        CrudRepository<T, String> repository = (CrudRepository<T, String>) repositoryFactory.getRepository(entityType);
        Optional<T> department = repository.findById(id);
        if (!department.isPresent()) {
            throw new DepartmentNotFoundException("Department Not available");
        }
        return department.get();
    }

    @Override
    public void deleteDepartmentById(String id, Class<?> entityType) {
        @SuppressWarnings("unchecked")
        CrudRepository<?, String> repository = (CrudRepository<?, String>) repositoryFactory.getRepository(entityType);
        repository.deleteById(id);
    }

    @Override
    public <T> T updateDepartment(String id, T department) {
        @SuppressWarnings("unchecked")
        CrudRepository<T, String> repository = (CrudRepository<T, String>) repositoryFactory.getRepository(department.getClass());
        Optional<T> depDB = repository.findById(id);
        T existingDepartment = depDB.get();
        
        // Implement a method to update only non-null fields
        updateNonNullFields(existingDepartment, department);
        
        return repository.save(existingDepartment);
    }

    private <T> void updateNonNullFields(T existingDepartment, T updatedDepartment) {
        // Implement the logic to update only non-null fields
        // This is a placeholder for the actual implementation
    }
}
