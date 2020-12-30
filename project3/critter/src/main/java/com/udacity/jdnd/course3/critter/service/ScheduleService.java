package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repo.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {
    private UserService userService;
    private PetService petService;
    private ScheduleRepository scheduleRepository;


    public ScheduleService(UserService userService, PetService petService, ScheduleRepository scheduleRepository) {
        this.userService = userService;
        this.petService = petService;
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule addSchedule(ScheduleDTO scheduleDTO) {
        // get employees
        List<Employee> allEmployees = userService.getAllEmployees();
        List<Employee> scheduleEmployees = new ArrayList<>();
        for(Employee e:allEmployees) {
            if(scheduleDTO.getEmployeeIds().contains(e.getId())){
                System.out.println("adding employee to schedule: " + e.getId());
                scheduleEmployees.add(e);
            }
        }
        //get pets? is there a better way for this? I think I do this like 3-4 times.
        // TODO: consider adding a getAllPetsById or similar
        List<Pet> allPets = petService.getAllPets();
        List<Pet> schedulePets = new ArrayList<>();
        for(Pet p:allPets) {
            if(scheduleDTO.getPetIds().contains(p.getId())){
                schedulePets.add(p);
            }
        }
        Schedule schedule = new Schedule(schedulePets,scheduleEmployees, scheduleDTO.getDate(), scheduleDTO.getActivities());
        schedule = scheduleRepository.save(schedule);
        return schedule;
    }

    public List<Schedule> getAllSchedules() {
        List<Schedule> schedules = (List<Schedule>) scheduleRepository.findAll();
        return schedules;
    }

    public List<Schedule> getSchedulesForPet(long id) {
        Pet p = petService.getPet(id);
        List<Schedule> schedules = scheduleRepository.findAllByPet(p);
        return schedules;
    }

    public List<Schedule> getSchedulesForEmployee(long employeeId) {
        Employee e = userService.getEmployee(employeeId);
        List<Schedule> schedules = scheduleRepository.findAllByEmployee(e);
        return schedules;
    }

    // This doesn't have a direct table query in schedule, so i'm just handling the logic here?
    public List<Schedule> getSchedulesForCustomer(long customerId) {
        Customer c = userService.getCustomer(customerId);
        List<Pet> pets = c.getPets();
        List<Schedule> schedules = new ArrayList<>();
        for(Pet pet:pets) {
            schedules.addAll(getSchedulesForPet(pet.getId()));
        }
        return schedules;
    }
}
