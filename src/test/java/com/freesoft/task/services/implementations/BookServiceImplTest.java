package com.freesoft.task.services.implementations;

import com.freesoft.task.entities.Book;
import com.freesoft.task.repositories.BookRepository;
import com.freesoft.task.services.exceptions.BookNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

//    @Mock
//    private BookMapper bookMapper;

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
        given(any(Book.class)).willReturn(Book.builder().build());

//        Page<Book> bookPage = bookService.getAllBooks(PageRequest.of(1,5));

        then(bookRepository).should(times(1)).findAll();


    }

    @Test
    void getBooksByAuthor() {

        given(bookRepository.findByAuthor("Tolstoy",any(Pageable.class))).willReturn(bookList);
        given(any(Book.class)).willReturn(Book.builder().build());

//        Page<Book> bookPage = bookService.getBooksByAuthor("Tolstoy",PageRequest.of(1,5));

        then(bookRepository).should(times(1)).findByAuthor("Tolstoy",any(Pageable.class));


    }

    @Test
    void getBooksByPublisher() {

        given(bookRepository.findByAuthor("Tolstoy",any(Pageable.class))).willReturn(bookList);
        given(any(Book.class)).willReturn(Book.builder().build());

//        Page<Book> bookDtoPage = bookService.getBooksByAuthor("Tolstoy",PageRequest.of(1,5));

        then(bookRepository).should(times(1)).findByAuthor("Tolstoy",PageRequest.of(1,5));


    }

    @Test
    void getBooksByPublisherAndAuthor() {

        given(bookRepository.findByPublisherAndAuthor("Tolstoy","Penguin",any(Pageable.class))).willReturn(bookList);
        given(any(Book.class)).willReturn(Book.builder().build());

//        Page<Book> bookPage = bookService.getBooksByPublisherAndAuthor("Tolstoy","Penguin",PageRequest.of(1,5));

        then(bookRepository).should(times(1)).findByPublisherAndAuthor("Tolstoy","Penguin",any(Pageable.class));


    }

    @Test
    void getBookByName() {

        given(bookRepository.findByName("Tolstoy",any(Pageable.class))).willReturn(bookList);
        given(any(Book.class)).willReturn(Book.builder().build());

//        Page<Book> bookPage = bookService.getBooksByName("Tolstoy",PageRequest.of(1,5));

        then(bookRepository).should(times(1)).findByName("Tolstoy",any(Pageable.class));

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
        given(any(Book.class)).willReturn(Book.builder().build());

//        Book bookPage = bookService.getBookByISBN("Tolstoy");

        then(bookRepository).should(times(1)).findByIsbn("Tolstoy");


    }
}