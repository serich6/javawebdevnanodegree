package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repo.CustomerRepository;
import com.udacity.jdnd.course3.critter.repo.EmployeeRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Service
@Transactional
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

    // EMPLOYEE METHODS

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

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        // get the user by id
        Employee employee = employeeRepository.findById(employeeId).get();
        // if we don't have any set days, just replace the set
        if (employee.getDaysAvailable()== null) {
            employee.setDaysAvailable(daysAvailable);
        }
        // if we DO have existing days, just add the diff? for update case?
        else {

        }

        // save the updated employee info
        employeeRepository.save(employee);
    }

    // CUSTOMER METHODS

    public Customer addCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.save(new Customer(customerDTO.getName(),customerDTO.getPhoneNumber()));
        return customer;
    }

    // Added secondary constructor to get rid of dupe code in PetService (needed to skip DTO part?)
    public Customer addCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }

    public Customer getCustomerDTO(Long id){
        return getCustomerDTO(id);
    }

    public Customer getCustomer(Long id){
        Customer customer = customerRepository.findById(id).get();
        return customer;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        return customers;
    }

    public Customer getCustomerByPet(long petId) {
       return petService.getOwnerByPetID(petId);
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        return employees;
    }
}
