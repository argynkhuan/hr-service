package io.hrmodule.hrservice.repository;

import io.hrmodule.hrservice.document.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

}
