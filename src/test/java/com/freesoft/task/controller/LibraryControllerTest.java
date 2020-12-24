package com.freesoft.task.controller;

import com.freesoft.task.entities.Author;
import com.freesoft.task.entities.Book;
import com.freesoft.task.entities.Publisher;
import com.freesoft.task.services.AuthorService;
import com.freesoft.task.services.BookService;
import com.freesoft.task.services.PublisherService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@AutoConfigureTestDatabase
@WebMvcTest(controllers = LibraryController.class)
class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private PublisherService publisherService;

    @MockBean
    private Map<String,Object> model;

    private Page<Book> bookPage;

    private Book book;
    private Author author;
    private Publisher publisher;

    @BeforeEach
    void setUp() {

        publisher = Publisher.builder().name("Penguin").build();

        author = Author.builder().name("Franz").surname("Kafka").build();

        book = Book.builder().name("die Verwandlung").id(1L).author(author).publisher(publisher).build();

        author.addBooks(book);
        publisher.addBook(book);

        bookPage = new PageImpl<>(Arrays.asList(book), PageRequest.of(0,10),1);

    }

    @Test
    @WithMockUser(value = "spring")
    void bookList() throws Exception {

        given(bookService.getAllBooks(any(Pageable.class))).willReturn(bookPage);

        mockMvc.perform(get("/books/list"))
                .andExpect(view().name("book-list"))
                .andExpect(model().attribute("books", Matchers.hasProperty("totalElements", equalTo(1L))));

    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER","ADMIN"})
    void bookSave() throws Exception {

        //Geri dönülüp tekrar test edilecek.

        mockMvc.perform(post("/books/save")
                .param("name","Kitap")
                .param("description","desc")
                .param("author.name","Berker")
                .param("author.surname","Igdir")
                .param("publisher.name","Epsilon")
                .param("isbn","L123"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("book"));

    }

    @Test
    void bookdelete() {
    }
}