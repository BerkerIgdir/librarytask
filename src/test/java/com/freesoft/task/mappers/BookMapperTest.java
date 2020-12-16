package com.freesoft.task.mappers;

import com.freesoft.task.dtos.BookDto;
import com.freesoft.task.entities.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

// An early integration test for mapper.

@Slf4j
@SpringBootTest // Context is called
class BookMapperTest {

    private Book book;

    private BookDto bookDto;

    @Autowired
    private BookMapper bookMapper;

    @BeforeEach
    void setUp() {
        book = Book.builder().name("der Steppenwolf").id(1L).build();

        bookDto = bookMapper.toDto(book);

    }

    @Test
    void bookToDto() {

    assertNotNull(bookDto);

    assertEquals(bookDto.getName(),book.getName());

    }

    @Test
    void toBook() {

        bookDto.setName("die Angst");

        book = bookMapper.toBook(bookDto);

        assertNotNull(book);

        assertEquals("die Angst",book.getName());
    }
}