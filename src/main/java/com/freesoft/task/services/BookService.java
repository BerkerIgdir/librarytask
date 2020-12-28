package com.freesoft.task.services;



import com.freesoft.task.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//An interface for service layer, which can be adjusted in the future to meet the new requirements.

public interface BookService {

    Page<Book> getAllBooks(Pageable pageable);
    Page<Book> getBooksByAuthor(String author,Pageable pageable);
    Page<Book> getBooksByPublisher(String publisher,Pageable pageable);
    Page<Book> getBooksByPublisherAndAuthor(String publisher,String author,Pageable pageable);

    Page<Book> getBooksByName(String name,Pageable pageable);
    Book getBookByISBN(String isbn);
    Book getBookById(Long id);
    void save(Book book);
    void delete(Book book);
}
