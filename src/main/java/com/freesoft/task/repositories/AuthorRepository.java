package com.freesoft.task.repositories;

import com.freesoft.task.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    Optional<Author> findByNameAndSurname(String name, String surname);
    Page<Author> findByName(String name, Pageable pageable);
    Page<Author> findBySurname(String name,Pageable pageable);

}
