package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.repo.ScheduleRepository;
import org.springframework.stereotype.Service;

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
}
