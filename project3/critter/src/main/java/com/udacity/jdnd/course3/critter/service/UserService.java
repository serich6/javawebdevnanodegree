package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repo.CustomerRepository;
import com.udacity.jdnd.course3.critter.repo.EmployeeRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;
    private PetService petService;

    // REFERENCE NOTE: Adding @lazy from https://stackoverflow.com/questions/40695893/spring-security-circular-bean-dependency
    // to fix circular dependency issue
    public UserService(CustomerRepository customerRepository, EmployeeRepository employeeRepository, @Lazy PetService petService) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.petService = petService;
    }

    public Employee addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.save(new Employee(employeeDTO.getName(),
                                                    employeeDTO.getDaysAvailable(),
                                                    employeeDTO.getSkills()));
        return employee;
    }

    public Employee getEmployeeDTO(Long id){
        return getEmployee(id);
    }

    public Employee getEmployee(Long id){
        Employee employee = employeeRepository.findById(id).get();
        return employee;
    }
}
