package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.repo.PetRepository;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    private PetRepository petRepository;
    private UserService userService;

    PetService(PetRepository petRepository, UserService userService){
        this.petRepository = petRepository;
        this.userService = userService;
    }
}
