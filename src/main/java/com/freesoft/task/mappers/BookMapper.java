package com.freesoft.task.mappers;

import com.freesoft.task.dtos.BookDto;
import com.freesoft.task.entities.Book;
import org.mapstruct.Mapper;

// A simple mapper, it can be customized via various methods.

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto toDto(Book book);
    Book toBook(BookDto bookDto);
}
