package com.freesoft.task.services.implementations;

import com.freesoft.task.entities.Author;
import com.freesoft.task.entities.Book;
import com.freesoft.task.entities.Publisher;
import com.freesoft.task.services.AuthorService;
import com.freesoft.task.services.BookService;
import com.freesoft.task.services.PublisherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class ServiceIntegrationTest {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private BookService bookService;

    private Book book;

    private Author author;

    private Publisher publisher;

    @BeforeEach
    void setUp(){
        book = Book.builder().name("Kitap").isbn("L1234563").description("aciklama").build();
        author = Author.builder().name("Yazard").surname("Soyad").build();
        publisher = Publisher.builder().name("Kitap").build();
    }

    @Test
    void save(){

        author.addBooks(book);
        publisher.addBook(book);
        publisherService.save(publisher);
        authorService.save(author);
    }

}
