package com.pidev.backend.ServiceImpl;

import com.pidev.backend.Entity.Department;
import com.pidev.backend.Entity.Role;
import com.pidev.backend.Entity.User;
import com.pidev.backend.Repository.DepartmentRepository;
import com.pidev.backend.Repository.UserRepository;
import com.pidev.backend.Service.DepartmentService;
import com.pidev.backend.Service.UserService;
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
    public Department getDepartmentById(Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.orElse(null);
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Long id, Department department) {
        if (departmentRepository.existsById(id)) {
            department.setDepartmentId(id);
            return departmentRepository.save(department);
        }
        return null;
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteDepartmentByDepartmentId(id);
    }

    @Override
    public Department addTeacherToDepartment(String login, Long departmentId) {
        User teacher = userRepository.findUserByLogin(login);
        Department department = departmentRepository.findDepartmentByDepartmentId(departmentId);
        if(teacher.getRole()== Role.TEACHER){
            if(department.getTeachers()==null){
                List<User> teachers = new ArrayList<>();
                department.setTeachers(teachers);
            }else {
                department.getTeachers().add(teacher);


            }
            departmentRepository.save(department);
            return department;

        }
        return null;

    }


}
