package com.employee.employeeapi.controller;

import com.employee.employeeapi.model.Department;
import com.employee.employeeapi.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/department")
public class DepartmentController {
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> getAll(){
        final List<Department> departments = departmentService.getAll();

        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Department> create(@RequestBody @Valid Department department){
        Department newDepartment = departmentService.create(department);
        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Department> getById(@PathVariable("id") Long id){
        Optional<Department> department = departmentService.getById(id);
        if (department.isEmpty()){ return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        return new ResponseEntity<>(department.get(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Department> update(@PathVariable("id") Long id, @RequestBody Department department){
        department.setId(id);
        Department updatedDepartment = departmentService.update(department);

        if (updatedDepartment == null) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {departmentService.delete(id);}
}
