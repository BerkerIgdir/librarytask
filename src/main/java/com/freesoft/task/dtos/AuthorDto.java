package com.freesoft.task.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.freesoft.task.entities.Book;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

// A dto should be immutable and stateless, but unfortunately due to fact, that I could not come up with a solution where I can combine MVC and an immutable dto
// I won't be using immutable dtos


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorDto {

    @NotBlank(message = "Name can not be blank")
    String name;
    @NotBlank(message = "Surname can not be blank")
    String surname;

    @NotNull(message = "This field can not be left null")
    @NotEmpty(message = "Every author must have at least one book")
    Set<Book> books = new HashSet<>();

}
