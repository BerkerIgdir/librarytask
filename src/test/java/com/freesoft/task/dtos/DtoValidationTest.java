package com.freesoft.task.dtos;



import com.freesoft.task.entities.Publisher;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

import java.util.Set;

// A violation test

@Slf4j
class DtoValidationTest {


    private Validator validator;

    private BookDto bookDto;

    private  AuthorDto authorDto;

    private PublisherDto publisherDto;

    @BeforeEach
    void setUp() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        bookDto = BookDto.builder().build();
        authorDto = AuthorDto.builder().build();
        publisherDto = PublisherDto.builder().build();
    }

    @Test
    @DisplayName("Not Null Exception Test")
    void validationTest(){

        Set<ConstraintViolation<BookDto>> bookDtoViolations = validator.validate(bookDto);
        Set<ConstraintViolation<AuthorDto>> authorDtoViolations = validator.validate(authorDto);
        Set<ConstraintViolation<PublisherDto>> publisherDtoViolations = validator.validate(publisherDto);
        bookDtoViolations.stream().forEach(bookDtoConstraintViolation -> log.info(bookDtoConstraintViolation.getMessage()));
        authorDtoViolations.stream().forEach(bookDtoConstraintViolation -> log.info(bookDtoConstraintViolation.getMessage()));
        publisherDtoViolations.stream().forEach(bookDtoConstraintViolation -> log.info(bookDtoConstraintViolation.getMessage()));

    }
}