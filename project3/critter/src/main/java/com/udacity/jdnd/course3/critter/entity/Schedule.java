package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.entity.entityEnums.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="schedule")
public class Schedule {
    @Id
    @GeneratedValue
    private long id;
    @ManyToMany
    private List<Pet> pets;
    @ManyToMany
    private List<Employee> employees;
    private LocalDate date;
    @ElementCollection
    private Set<EmployeeSkill> activities;

    public Schedule(List<Pet> pets, List<Employee> employees, LocalDate date, Set<EmployeeSkill> activities) {
        this.pets = pets;
        this.employees = employees;
        this.date = date;
        this.activities = activities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
