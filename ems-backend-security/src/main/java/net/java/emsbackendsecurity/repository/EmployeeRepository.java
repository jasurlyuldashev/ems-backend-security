package net.java.emsbackendsecurity.repository;

import net.java.emsbackendsecurity.entity.Employee;
import net.java.emsbackendsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
