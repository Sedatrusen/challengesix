package com.enoca.challengesix.repositories;

import com.enoca.challengesix.entities.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,Long>     {
}
