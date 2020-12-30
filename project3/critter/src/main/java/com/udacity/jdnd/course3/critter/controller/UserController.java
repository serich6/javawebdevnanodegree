package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.entityEnums.EmployeeSkill;
import com.udacity.jdnd.course3.critter.service.UserService;
import org.checkerframework.checker.units.qual.C;
import org.h2.engine.User;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return convertCustomerToDTO(userService.addCustomer(customerDTO));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        //get the list of customers from user service
        // for each one, convert and add to a dto array
        // return the dto array
        List<CustomerDTO> dtos = new ArrayList<>();
        List<Customer> customers = userService.getAllCustomers();
        for(Customer customer:customers) {
            dtos.add(convertCustomerToDTO(customer));
        }
        return dtos;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        return convertCustomerToDTO(userService.getCustomerByPet(petId));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return convertEmployeeToTDO(userService.addEmployee(employeeDTO));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return convertEmployeeToTDO(userService.getEmployeeDTO(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        userService.setAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        // Get the days/skills needed
        DayOfWeek day = employeeDTO.getDate().getDayOfWeek();
        Set<EmployeeSkill> skills = employeeDTO.getSkills();

        // Get employees available on those days w/skills, add then to a return struct
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        // TODO: Is there a quicker/more efficient way to do this?
        List<Employee> employees = userService.getAllEmployees();

        for(Employee employee:employees) {
            //if the skills & days working match (sets), add it to the array
            if(employee.getSkills().containsAll(skills) && employee.getDaysAvailable().contains(day)){
                System.out.println("Found a matching employee");
                employeeDTOS.add(convertEmployeeToTDO(employee));
            }
        }
        return employeeDTOS;
    }

    /*
    // CONVERSIONS
     */

    // REFERENCE: conversions from lesson 2, video 17.1
    private EmployeeDTO convertEmployeeToTDO(Employee employee){
        EmployeeDTO dto = new EmployeeDTO();
        BeanUtils.copyProperties(employee, dto);
        return dto;
    }

    private CustomerDTO convertCustomerToDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        BeanUtils.copyProperties(customer, dto);
        List<Long> petIds = new ArrayList<>();
        if (customer.getPets() != null) {
            petIds = customer.getPets().stream().map(Pet::getId).collect(Collectors.toList());
        }
        dto.setPetIds(petIds);
        return dto;
    }
}
