package com.freesoft.task.services;


import com.freesoft.task.entities.Author;
import com.freesoft.task.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AuthorService {

    Author getByNameAndSurname(String name, String surname);

    Page<Author> getAuthorsByName(String name, Pageable pageable);
    Page<Author> getAuthorsBySurname(String name, Pageable pageable);
    void delete(Author author);
//    void addBook(Book book);
    void save(Author author);
}
