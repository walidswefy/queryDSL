package com.example.querydsl.repository;

import com.example.querydsl.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

/**
 * @author walid.sewaify
 * @since 13-Nov-20
 */
public interface EmployeeRepository extends MongoRepository<Employee, String>, QuerydslPredicateExecutor<Employee> {
    @Query("{ 'name' : { $regex: ?0 } }")
    List<Employee> findByRegexpName(String regexp);
}
