package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.entity.entityEnums.PetType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="pet")
public class Pet {
    @Id
    @GeneratedValue
    private Long id;
    private PetType type;
    private String name;
    // extra fields from dto
    private LocalDate birthDate;
    private String notes;
    @ManyToOne
    private Customer owner;

    // Added a separate constructor for PetService-> addPet. Check to see if the other is still in use?
    public Pet(String name, PetType type, Customer owner) {
        this.name = name;
        this.type = type;
        this.owner = owner;
    }

    public Pet(String name, PetType type) {
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public Customer getOwner() {
        return owner;
    }
}
