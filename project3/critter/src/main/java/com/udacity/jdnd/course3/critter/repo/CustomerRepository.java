package com.udacity.jdnd.course3.critter.repo;

import com.udacity.jdnd.course3.critter.entity.Customer;
import org.checkerframework.common.util.report.qual.ReportCreation;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.CrudRepository;

@ReportCreation
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
