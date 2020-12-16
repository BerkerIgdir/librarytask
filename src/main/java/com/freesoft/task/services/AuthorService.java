package com.freesoft.task.services;

import com.freesoft.task.dtos.AuthorDto;
import com.freesoft.task.entities.Author;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AuthorService {

    AuthorDto getByNameAndSurname(String name, String surname);
    Page<Author> getAuthorsByName(String name);
    Page<Author> getAuthorsBySurname(String name);

}
