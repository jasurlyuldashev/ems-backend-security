package net.java.emsbackendsecurity.service.impl;

import lombok.RequiredArgsConstructor;
import net.java.emsbackendsecurity.dto.EmployeeDto;
import net.java.emsbackendsecurity.entity.Employee;
import net.java.emsbackendsecurity.exeption.ResourceNotFoundException;
import net.java.emsbackendsecurity.mapper.EmployeeMapper;
import net.java.emsbackendsecurity.repository.EmployeeRepository;
import net.java.emsbackendsecurity.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repo;


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee saved = repo.save(employee);
        return EmployeeMapper.mapToEmployeeDto(saved);
    }

    @Override
    public EmployeeDto getEmployeeByID(Long employeeId) {
        Employee employee = repo.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee does not exist!  id : " + employeeId));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = repo.findAll();
        return employees.stream().map(EmployeeMapper::mapToEmployeeDto).toList();
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = repo.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee does not exist!  id : " + employeeId));

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updated = repo.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updated);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = repo.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee does not exist!  id : " + employeeId));

        repo.deleteById(employeeId);

    }
}
