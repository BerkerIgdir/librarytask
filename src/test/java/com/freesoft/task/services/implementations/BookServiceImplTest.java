package com.freesoft.task.services.implementations;

import com.freesoft.task.controller.LibraryController;
import com.freesoft.task.entities.Book;
import com.freesoft.task.repositories.BookRepository;
import com.freesoft.task.services.exceptions.BookNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;


// BDD test of service layer.
//Test is only applied to this service implementation because the rest(author and publisher services) is virtually identical.


@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Page<Book> bookList;

    @BeforeEach
    void setUp() {

        Book book = Book.builder().id(1L).name("Berker").isbn("adfafa").description("Aciklama1").build();

        Book book1 = Book.builder().id(1L).name("Efe Aras").isbn("adfafa").description("Aciklama2").build();

        bookList = new PageImpl<>(Arrays.asList(book,book1),PageRequest.of(1,5),2);

    }

    @Test
    void getAllBooks() {

        given(bookRepository.findAll(any(Pageable.class))).willReturn(bookList);

        Page<Book> bookPage = bookService.getAllBooks(PageRequest.of(1,5));

        then(bookRepository).should(times(1)).findAll(any(Pageable.class));

    }

    @Test
    void getBooksByAuthor() {

        given(bookRepository.findByAuthor(anyString(),any(Pageable.class))).willReturn(bookList);

        bookService.getBooksByAuthor("Tolstoy",PageRequest.of(1,5));

        then(bookRepository).should(times(1)).findByAuthor(anyString(),any(Pageable.class));

    }

    @Test
    void getBooksByPublisher() {

        given(bookRepository.findByPublisher(anyString(),any(Pageable.class))).willReturn(bookList);

        bookService.getBooksByPublisher("Tolstoy",PageRequest.of(1,5));

        then(bookRepository).should(times(1)).findByPublisher(anyString(),any(Pageable.class));

    }

    @Test
    void getBooksByPublisherAndAuthor() {

        given(bookRepository.findByPublisherAndAuthor(anyString(),anyString(),any(Pageable.class))).willReturn(bookList);

        bookService.getBooksByPublisherAndAuthor("Tolstoy","Penguin",PageRequest.of(1,5));

        then(bookRepository).should(times(1)).findByPublisherAndAuthor(anyString(),anyString(),any(Pageable.class));

    }

    @Test
    void getBookByName() {

        given(bookRepository.findByName(anyString(),any(Pageable.class))).willReturn(bookList);


        bookService.getBooksByName("Tolstoy",PageRequest.of(1,5));

        then(bookRepository).should(times(1)).findByName(anyString(),any(Pageable.class));

    }

    @Test
    void getBookByIsbnThrowsException() {

        willThrow(BookNotFoundException.class).given(bookRepository).findByIsbn(any(String.class));


        try{
            bookService.getBookByISBN("Tolstoy");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @Test
    void getBookByISBN() {

        given(bookRepository.findByIsbn("Tolstoy")).willReturn(Optional.of(bookList.toList().get(0)));

        bookService.getBookByISBN("Tolstoy");

        then(bookRepository).should(times(1)).findByIsbn("Tolstoy");


    }
}