package com.freesoft.task.repositories;

import com.freesoft.task.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

// Entities are intentionally put into a list in this layer.
// It could have been put into a Page, but we will check and transform them in service layer anyway.

public interface BookRepository extends JpaRepository<Book,Long> {

    Page<Book> findAll(Pageable pageable);
    Page<Book> findByAuthor(String author, Pageable pageable);
    Page<Book>findByPublisher(String publisher,Pageable pageable);
    Page<Book> findByPublisherAndAuthor(String publisher,String author,Pageable pageable);

    Page<Book> findByName(String name,Pageable pageable);
    Optional<Book> findByIsbn(String name);
}
