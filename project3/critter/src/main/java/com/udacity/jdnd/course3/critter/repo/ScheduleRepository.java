package com.udacity.jdnd.course3.critter.repo;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends CrudRepository <Schedule, Long> {
    @Query("select s from Schedule s where :pet member of s.pets")
    List<Schedule> findAllByPet(@Param("pet") Pet pet);
//    @Query("select s from Schedule s where :customer member of s.customer")
//    List<Schedule> findAllByCustomer(@Param("customer") Customer customer);
    @Query("select s from Schedule s where :employee member of s.employees")
    List<Schedule> findAllByEmployee(@Param("employee") Employee employee);
}
