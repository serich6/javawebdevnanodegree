package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.entity.entityEnums.PetType;

import javax.persistence.*;

@Entity
@Table(name="pet")
public class Pet {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private PetType type;
    @ManyToOne
    private Customer owner;

    public Pet() {

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
