package com.freesoft.task.mappers;

import com.freesoft.task.dtos.AuthorDto;
import com.freesoft.task.entities.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDto toDto(Author author);
    Author toAuthor(AuthorDto authorDto);
}
