package com.buffer.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buffer.demo.entity.Department;
import com.buffer.demo.entity.sql.DepartmentSql;
import com.buffer.demo.repository.DepartmentRepository;
import com.buffer.demo.repository.DepartmentRepositorySql;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MigrationService {
  @Autowired
  private DepartmentRepository departmentRepository;

  @Autowired
  private DepartmentRepositorySql departmentRepositorySql;
  
  @Transactional
  public void migrateData() {
    List<Department> mongoDepartments = departmentRepository.findAll();
    System.out.println("mongoDepartments: " + mongoDepartments);  
    Logger logger = LoggerFactory.getLogger(MigrationService.class);
    logger.info("Starting data migration...");
    
    for(Department mongoDepartment : mongoDepartments) {
      DepartmentSql sqlDepartment = new DepartmentSql();
      sqlDepartment.setDepartmentId(mongoDepartment.getDepartmentId());
      sqlDepartment.setDepartmentName(mongoDepartment.getDepartmentName());
      sqlDepartment.setDepartmentAddress(mongoDepartment.getDepartmentAddress());
      sqlDepartment.setDepartmentCode(mongoDepartment.getDepartmentCode());
      departmentRepositorySql.save(sqlDepartment);
      
      logger.debug("Transferring department: {}", sqlDepartment);
    }
    
    logger.info("Data migration completed.");
  }

}
