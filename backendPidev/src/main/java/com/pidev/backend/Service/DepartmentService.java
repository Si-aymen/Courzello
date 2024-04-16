package com.pidev.backend.Service;

import com.pidev.backend.Entity.Department;
import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartments();
    Department getDepartmentById(String departmentId);
    Department createDepartment(Department user);
    void deleteDepartment(String departmentId);
    Department updateDepartment(String id, Department department);
<<<<<<< HEAD

    Department affectTeacherToDep(String login, String id);
=======
>>>>>>> 9c94761f2eb0aa9a853227c20cfce771558f1a98
}
