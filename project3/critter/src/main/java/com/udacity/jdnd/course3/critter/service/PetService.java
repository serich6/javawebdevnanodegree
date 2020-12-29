package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
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

    public Customer getOwnerByPetID(Long id) {
        Pet pet = petRepository.findById(id).get();
        return pet.getOwner();
    }

    public Pet addPet(PetDTO petDTO) {
        Pet pet = petRepository.save(new Pet(petDTO.getName(), petDTO.getType()));
        return pet;
    }
}
