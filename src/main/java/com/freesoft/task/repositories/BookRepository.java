package com.freesoft.task.repositories;

import com.freesoft.task.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// Entities are intentionally put into a list in this layer.
// It could have been put into a Page, but we will check and transform them in service layer anyway.

public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findByAuthor(String author);
    List<Book> findByPublisher(String publisher);
    List<Book> findByPublisherAndAuthor(String publisher,String author);

    Optional<Book> findByName(String name);
    Optional<Book> findByIsbn(String name);
}
