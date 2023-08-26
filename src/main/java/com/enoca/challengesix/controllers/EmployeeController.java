package com.enoca.challengesix.controllers;

import com.enoca.challengesix.entities.Employee;
import com.enoca.challengesix.exception.EmployeeNotFoundException;
import com.enoca.challengesix.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }
    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/employees")
    List<Employee> all() {
        return (List<Employee>) repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        if (newEmployee.getEmail().isEmpty()){
           throw new RuntimeException("Mail Can Not Be Empty");
        }else if (newEmployee.getName().isEmpty()){
            throw new RuntimeException("Name Can Not Be Empty");
        } else if (newEmployee.getJobId()==null) {
            throw new RuntimeException("Job Id Can Not Be Empty");
        }else {
            return repository.save(newEmployee);
        }

    }

    // Single item

    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setEmail(newEmployee.getEmail());
                    employee.setJobId(newEmployee.getJobId());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}


