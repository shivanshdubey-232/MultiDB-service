package com.buffer.demo.entity.sql;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class DepartmentSql {
    @Id
    @Column(name = "department_id") 
    private String departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "department_address") 
    private String departmentAddress;

    @Column(name = "department_code") 
    private String departmentCode;
}
