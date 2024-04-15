package com.pidev.backend.Controller;

import com.pidev.backend.Entity.Department;
import com.pidev.backend.Repository.DepartmentRepository;
import com.pidev.backend.Service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*",exposedHeaders="Access-Control-Allow-Origin" )
@RequestMapping("/Departments")
public class DepartmentController {
    private DepartmentRepository departmentRepository;
    private DepartmentService departmentService;


    @GetMapping("/GetAllDepartments")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartmentbyId(@PathVariable String id){
        return departmentService.getDepartmentById(id);
    }

    @PostMapping("/Save/department")
    public Department createDepartment(@RequestBody Department department){
        return departmentService.createDepartment(department);
    }
    @PutMapping("{id}")
    public Department updateDepartment(@PathVariable String id,@RequestBody Department dep)
    {
        return departmentService.updateDepartment(id,dep);
    }

    @DeleteMapping("/{id}")
    public void deleteDep(@PathVariable String id){
        departmentService.deleteDepartment(id);
    }


    @PostMapping("/{id}/{login}")
    public Department addteachertodep(@PathVariable String id,@PathVariable String login ){
        return departmentService.affectTeacherToDep(login,id);
    }

}
