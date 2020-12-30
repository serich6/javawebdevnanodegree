package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private ScheduleService scheduleService;

    ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return convertScheduleToDTO(scheduleService.addSchedule(scheduleDTO));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<ScheduleDTO> dtos = new ArrayList<>();
        List<Schedule> schedules = scheduleService.getAllSchedules();
        for(Schedule s:schedules) {
            dtos.add(convertScheduleToDTO(s));
        }
        return dtos;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        throw new UnsupportedOperationException();
    }

    // REFERENCE: conversions from lesson 2, video 17.1
    private ScheduleDTO convertScheduleToDTO(Schedule schedule){
        ScheduleDTO dto = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, dto);
        // set the employee ids?
        if (schedule.getEmployees() != null) {
            List<Employee> employees = schedule.getEmployees();
            List<Long> employeeIds = new ArrayList<>();
            for(Employee e: employees) {
                employeeIds.add(e.getId());
            }
            dto.setEmployeeIds(employeeIds);
        }
        // add pets
        if (schedule.getPets() != null) {
            List<Pet> pets = schedule.getPets();
            List<Long> petIds = new ArrayList<>();
            for(Pet p: pets) {
                petIds.add(p.getId());
            }
            dto.setPetIds(petIds);
        }
        return dto;
    }
}
