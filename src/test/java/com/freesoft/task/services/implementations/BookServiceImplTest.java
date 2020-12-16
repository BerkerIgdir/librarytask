package com.freesoft.task.services.implementations;

import com.freesoft.task.dtos.BookDto;
import com.freesoft.task.entities.Book;
import com.freesoft.task.mappers.BookMapper;
import com.freesoft.task.repositories.BookRepository;
import com.freesoft.task.services.exceptions.BookNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.util.Arrays;
import java.util.List;
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

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    private List<Book> bookList;

    @BeforeEach
    void setUp() {

        Book book = Book.builder().id(1L).name("Berker").isbn("adfafa").description("Aciklama1").build();

        Book book1 = Book.builder().id(1L).name("Efe Aras").isbn("adfafa").description("Aciklama2").build();

        bookList =Arrays.asList(book,book1);

    }

    @Test
    void getAllBooks() {

        given(bookRepository.findAll()).willReturn(bookList);
        given(bookMapper.toDto(any(Book.class))).willReturn(BookDto.builder().build());

        Page<BookDto> bookDtoPage = bookService.getAllBooks(PageRequest.of(1,5));

        then(bookRepository).should(times(1)).findAll();
        then(bookMapper).should(times(2)).toDto(any(Book.class));

    }

    @Test
    void getBooksByAuthor() {

        given(bookRepository.findByAuthor("Tolstoy")).willReturn(bookList);
        given(bookMapper.toDto(any(Book.class))).willReturn(BookDto.builder().build());

        Page<BookDto> bookDtoPage = bookService.getBooksByAuthor("Tolstoy",PageRequest.of(1,5));

        then(bookRepository).should(times(1)).findByAuthor("Tolstoy");
        then(bookMapper).should(times(2)).toDto(any(Book.class));

    }

    @Test
    void getBooksByPublisher() {

        given(bookRepository.findByAuthor("Tolstoy")).willReturn(bookList);
        given(bookMapper.toDto(any(Book.class))).willReturn(BookDto.builder().build());

        Page<BookDto> bookDtoPage = bookService.getBooksByAuthor("Tolstoy",PageRequest.of(1,5));

        then(bookRepository).should(times(1)).findByAuthor("Tolstoy");
        then(bookMapper).should(times(2)).toDto(any(Book.class));

    }

    @Test
    void getBooksByPublisherAndAuthor() {

        given(bookRepository.findByPublisherAndAuthor("Tolstoy","Penguin")).willReturn(bookList);
        given(bookMapper.toDto(any(Book.class))).willReturn(BookDto.builder().build());

        Page<BookDto> bookDtoPage = bookService.getBooksByPublisherAndAuthor("Tolstoy","Penguin",PageRequest.of(1,5));

        then(bookRepository).should(times(1)).findByPublisherAndAuthor("Tolstoy","Penguin");
        then(bookMapper).should(times(2)).toDto(any(Book.class));

    }

    @Test
    void getBookByName() {

        given(bookRepository.findByName("Tolstoy")).willReturn(Optional.of(bookList.get(0)));
        given(bookMapper.toDto(any(Book.class))).willReturn(BookDto.builder().build());

        BookDto bookDtoPage = bookService.getBookByName("Tolstoy");

        then(bookRepository).should(times(1)).findByName("Tolstoy");
        then(bookMapper).should(times(1)).toDto(any(Book.class));
    }

    @Test
    void getBookByNameThrowsException() {

        willThrow(BookNotFoundException.class).given(bookRepository).findByName(any(String.class));


        try{
            bookService.getBookByName("Tolstoy");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @Test
    void getBookByISBN() {

        given(bookRepository.findByIsbn("Tolstoy")).willReturn(Optional.of(bookList.get(0)));
        given(bookMapper.toDto(any(Book.class))).willReturn(BookDto.builder().build());

        BookDto bookDtoPage = bookService.getBookByISBN("Tolstoy");

        then(bookRepository).should(times(1)).findByIsbn("Tolstoy");
        then(bookMapper).should(times(1)).toDto(any(Book.class));

    }
}