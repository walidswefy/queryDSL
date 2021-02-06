package com.example.querydsl.repository;

import com.example.querydsl.extensions.TestDataExtension;
import com.example.querydsl.model.QEmployee;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author walid.sewaify
 * @since 06-Feb-21
 */
@Tag("it")
@ExtendWith(TestDataExtension.class)
@DataMongoTest
class EmployeeRepositoryIT {
    @Autowired
    EmployeeRepository repository;

    @Test
    void testQueryDSL() {
        QEmployee query = new QEmployee("emp");
        Predicate predicate = query.name.matches("?mp?");
        assertEquals(repository.findAll(predicate), repository.findByRegexpName("?mp?"));
    }
}
