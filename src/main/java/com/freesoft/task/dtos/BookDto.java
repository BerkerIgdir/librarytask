package com.freesoft.task.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.freesoft.task.entities.Author;
import com.freesoft.task.entities.Publisher;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

// A dto should be immutable and stateless, but unfortunately due to fact, that I could not come up with a solution where I can combine MVC and an immutable dto
// I won't be using immutable dtos

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDto {

    @NotBlank (message = "Name can not be blank")
    String name;

    String secondaryName;
    String series;
    @NotBlank(message = "ISBN number can not be left null")
    String isbnNumber;
    @NotBlank(message = "Every book must have a description")
    String description;

    int count;

    @NotNull(message = "Every book has at least an author")
    Author author;
    @NotNull(message = "Every book has at least an author")
    Publisher publisher;

}
