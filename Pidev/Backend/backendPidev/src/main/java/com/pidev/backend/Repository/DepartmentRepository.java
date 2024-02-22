package com.pidev.backend.Repository;

import com.pidev.backend.Entity.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, Long> {
    void deleteDepartmentByDepartmentId(Long id);
    Department findDepartmentByDepartmentId(Long departmentId);
}
