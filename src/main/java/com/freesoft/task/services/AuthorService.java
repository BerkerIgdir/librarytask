package com.freesoft.task.services;

import com.freesoft.task.dtos.AuthorDto;
import com.freesoft.task.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService {

    AuthorDto getByNameAndSurname(String name, String surname);
    Page<AuthorDto> getAuthorsByName(String name, Pageable pageable);
    Page<AuthorDto> getAuthorsBySurname(String name, Pageable pageable);

}
