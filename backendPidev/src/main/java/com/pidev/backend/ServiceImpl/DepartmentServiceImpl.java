package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Entity.Department;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Repository.DepartmentRepository;
import com.pidev.backend.Repository.UserRepository;
import com.pidev.backend.Service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    private UserRepository userRepository;

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
        User u = userRepository.findUserByLogin(login);
        Department d = departmentRepository.findDepartmentByDepartmentId(id);

        if(d.getTeachers()==null)
        {
            List<User> teachers = new ArrayList<>();
            teachers.add(u);
            d.setTeachers(teachers);
        }
        else {
            d.getTeachers().add(u);
        }


        return departmentRepository.save(d);
    }
}
