package com.freesoft.task.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseEntityTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void builderInheritanceTest(){

        Publisher publisher = Publisher.builder().name("pub").build();

        assertNotNull(publisher);

    }

}