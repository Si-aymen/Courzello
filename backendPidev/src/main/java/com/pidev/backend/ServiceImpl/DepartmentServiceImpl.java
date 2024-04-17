package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Entity.Department;
import com.pidev.backend.Repository.DepartmentRepository;
import com.pidev.backend.Service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(String departmentId) {
        Optional<Department> department= departmentRepository.findById(departmentId);
        return department.orElse(null);
    }

    @Override
    public Department createDepartment(Department department) {
        if(departmentRepository.findDepartmentByDepartmentId(department.getDepartmentId())!=null)
        {
            throw new IllegalArgumentException("already exists");
        }


        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(String departmentId) {
        departmentRepository.deleteById(departmentId);

    }

    @Override
    public Department updateDepartment(String id, Department department) {
       Department department1 = departmentRepository.findDepartmentByDepartmentId(id);
       if(department1!=null){
           department.setDepartmentId(department1.getDepartmentId());
            return departmentRepository.save(department);
       }
        return null;
    }

    @Override
    public Department affectTeacherToDep(String login, String id) {
        return null;
    }
}
