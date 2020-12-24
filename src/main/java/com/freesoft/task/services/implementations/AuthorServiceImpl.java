package com.freesoft.task.services.implementations;


import com.freesoft.task.entities.Author;
import com.freesoft.task.repositories.AuthorRepository;
import com.freesoft.task.services.AuthorService;
import com.freesoft.task.services.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;



@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private final AuthorRepository authorRepository;

    @Override
    public Author getByNameAndSurname(String name, String surname) {
        return authorRepository.findByNameAndSurname(name,surname).orElseThrow(BookNotFoundException::new);
    }

    @Override
    public Page<Author> getAuthorsByName(String name, Pageable pageable) {



        return authorRepository.findByName(name,pageable);
    }

    @Override
    public Page<Author> getAuthorsBySurname(String name, Pageable pageable) {

        return authorRepository.findBySurname(name,pageable);
    }

    @Override
    public void delete(Author author) {
        authorRepository.delete(author);
    }


    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }
}
