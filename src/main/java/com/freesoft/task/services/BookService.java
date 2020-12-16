package com.freesoft.task.services;


import com.freesoft.task.dtos.BookDto;
import org.springframework.data.domain.Page;

//An interface for service layer, which can be adjusted in the future to meet the new requirements.

public interface BookService {

    Page<BookDto> getAllBooks();
    Page<BookDto> getBooksByAuthor(String author);
    Page<BookDto> getBooksByPublisher(String publisher);
    Page<BookDto> getBooksByPublisherAndAuthor(String publisher,String author);

    BookDto getBookByName(String name);
    BookDto getBookByISBN(String isbn);

}
