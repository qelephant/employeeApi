package com.employee.employeeapi.service;

import com.employee.employeeapi.model.Department;
import com.employee.employeeapi.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentService {
    private DepartmentRepository departmentRepository;

    public List<Department> getAll(){ return departmentRepository.findAll(); };

    public Department create(Department department) { return departmentRepository.save(department); }

    public Optional<Department> getById(Long id) { return departmentRepository.findById(id); }

    public Department update(Department department) {
        Optional<Department> existingDepartment = departmentRepository.findById(department.getId());
        if (existingDepartment.isPresent()) {
            return existingDepartment.map(ee -> {
                ee.setName(department.getName());
                ee.setEmployee(department.getEmployee());
                return departmentRepository.save(ee);
            }).get();
        }
        return null;
    }

    public void delete(Long id) { departmentRepository.deleteById(id);}
}
