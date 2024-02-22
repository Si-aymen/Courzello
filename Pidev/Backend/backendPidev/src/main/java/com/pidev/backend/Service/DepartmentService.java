package com.pidev.backend.Service;

import com.pidev.backend.Entity.Department;
import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartments();
    Department getDepartmentById(Long id);
    Department createDepartment(Department department);
    Department updateDepartment(Long id, Department department);
    void deleteDepartment(Long id);

    public Department addTeacherToDepartment(String login, Long departmentId);
}
