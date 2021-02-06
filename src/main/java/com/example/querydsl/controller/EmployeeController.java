package com.example.querydsl.controller;

import com.example.querydsl.model.Employee;
import com.example.querydsl.repository.EmployeeRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author walid.sewaify
 * @since 13-Nov-20
 */
@RestController
@RequestMapping("/emp")
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeRepository repository;

    @GetMapping("/query")
    public ResponseEntity<List<Employee>> dynamicQuery(
        @QuerydslPredicate(root = Employee.class) Predicate predicate,
        Pageable page) {
        Page<Employee> result = repository.findAll(predicate, page);
        return ResponseEntity.ok().body(result.getContent());
    }
}
