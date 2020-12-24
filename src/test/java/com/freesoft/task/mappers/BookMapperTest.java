package com.freesoft.task.mappers;

import com.freesoft.task.dtos.BookDto;
import com.freesoft.task.dtos.PublisherDto;
import com.freesoft.task.entities.Book;
import com.freesoft.task.entities.Publisher;
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

    private PublisherDto publisherDto;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private PublisherMapper publisherMapper;

    @BeforeEach
    void setUp() {

       book = Book.builder().name("die Verwandlung").id(1L).build();

        publisherDto = PublisherDto.builder().name("Penguin").build();

//        AuthorDto authorDto = AuthorDto.builder().name("Franz").surname("Kafka").build();

        bookDto = BookDto.builder().name("die Verwandlung").description("ein Buch")
                .isbn("L123131").publisher(publisherDto).build();


        Publisher publisher = publisherMapper.toPublisher(publisherDto);


    }

    @Test
    void bookToDto() {

    assertNotNull(bookDto);

    assertEquals(bookDto.getName(),book.getName());

    }

    @Test
    void toBook() {

        bookDto.setName("die Angst");

        Publisher publisher = publisherMapper.toPublisher(publisherDto);

        book = bookMapper.toBook(bookDto);

        assertNotNull(book);
        assertEquals(publisher.getName(),book.getPublisher().getName());
        assertEquals("die Angst",book.getName());

    }

}