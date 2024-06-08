package com.buffer.demo.repository;
// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.buffer.demo.entity.Department;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, String>{

}
