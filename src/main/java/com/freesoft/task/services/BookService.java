package com.freesoft.task.services;


import com.freesoft.task.dtos.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//An interface for service layer, which can be adjusted in the future to meet the new requirements.

public interface BookService {

    Page<BookDto> getAllBooks(Pageable pageable);
    Page<BookDto> getBooksByAuthor(String author,Pageable pageable);
    Page<BookDto> getBooksByPublisher(String publisher,Pageable pageable);
    Page<BookDto> getBooksByPublisherAndAuthor(String publisher,String author,Pageable pageable);

    BookDto getBookByName(String name);
    BookDto getBookByISBN(String isbn);

}
