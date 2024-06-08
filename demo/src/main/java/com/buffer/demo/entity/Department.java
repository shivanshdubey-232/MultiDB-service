package com.buffer.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection = "departments")
@Data
public class Department {
    @Id
    private String departmentId; // Change to String as MongoDB generates ObjectId
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
}
