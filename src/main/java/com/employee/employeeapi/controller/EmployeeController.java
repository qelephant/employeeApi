package com.employee.employeeapi.controller;

import com.employee.employeeapi.model.Employee;
import com.employee.employeeapi.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAll(){
        final List<Employee> employees = employeeService.getAll();

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody @Valid Employee employee){
        final Employee newEmployee = employeeService.create(employee);

        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getById(@PathVariable("id") Long id) {
        final Optional<Employee> employee = employeeService.getById(id);

        if (employee.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(employee.get(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> update(@PathVariable("id") Long id, @RequestBody Employee employee) {
        employee.setId(id);
        final Employee updatedEmployee = employeeService.update(employee);

        if (updatedEmployee == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        employeeService.delete(id);
    }

}
