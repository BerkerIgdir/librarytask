package com.freesoft.task.repositories;

import com.freesoft.task.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    Optional<Author> findByNameAndSurname(String name, String surname);
    List<Author> findByName(String name);
    List<Author> findBySurname(String name);

}
