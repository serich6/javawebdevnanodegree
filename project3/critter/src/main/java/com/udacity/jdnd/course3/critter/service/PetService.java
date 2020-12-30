package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repo.PetRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<Pet> getPetsByOwner(Long ownerId) {
        Customer owner = userService.getCustomer(ownerId);
        List<Pet> pets = petRepository.findAllByOwner(owner);
        return pets;
    }

    public Pet addPet(PetDTO petDTO) {
        // Add the pet to the customer at this level?
        Customer owner = userService.getCustomer(petDTO.getOwnerId());
        Pet petToSave = new Pet(petDTO.getName(), petDTO.getType(), owner);
        Pet pet = petRepository.save(petToSave);
        // add the pet to the user list as well, then I think we need to force an update?
        List<Pet> ownersPets = owner.getPets();
        // if this is the first pet, we need to set the array with just this one
        if (ownersPets== null) {
            List<Pet> petList = new ArrayList<>();
            petList.add(pet);
            owner.setPets(petList);
        } else {
            //otherwise, add the pet to the array
            ownersPets.add(pet);
            owner.setPets(ownersPets);
        }
        //save/refresh customer?
        // TODO: Having issues here where we already have a DTO version of this, but I'm not seeing
        // a way to link this to the DTO conversion in usercontroller? Added the method directly below this, but it's a dupe
        userService.addCustomer(owner);
        return pet;
    }

    // Removed in favor of adding new addCustomer method to UserService
//    private CustomerDTO convertCustomerToDTO(Customer customer) {
//        CustomerDTO dto = new CustomerDTO();
//        BeanUtils.copyProperties(customer, dto);
//        return dto;
//    }

    public Pet getPetDTO(long petId) {
        return getPet(petId);
    }

    public Pet getPet(Long id){
        Pet pet = petRepository.findById(id).get();
        return pet;
    }

    public List<Pet> getAllPets() {
        List<Pet> pets = (List<Pet>) petRepository.findAll();
        return pets;
    }
}
