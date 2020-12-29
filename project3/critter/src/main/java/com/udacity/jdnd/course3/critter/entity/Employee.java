package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.entity.entityEnums.EmployeeSkill;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ElementCollection
    private Set<DayOfWeek> daysAvailable;
    @ElementCollection
    private Set<EmployeeSkill> skills;

    public Employee() {

    }

    public Employee(String name, Set<DayOfWeek> daysAvailable, Set<EmployeeSkill> skills) {
        this.name = name;
        this.daysAvailable = daysAvailable;
        this.skills = skills;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }
}
