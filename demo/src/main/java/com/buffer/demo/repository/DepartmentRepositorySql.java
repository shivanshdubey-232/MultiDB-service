package com.buffer.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buffer.demo.entity.sql.DepartmentSql;

@Repository
public interface DepartmentRepositorySql extends JpaRepository<DepartmentSql, String>{

}