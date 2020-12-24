package com.freesoft.task.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

// A dto should be immutable and stateless, but unfortunately, due to fact, that I could not come up with a solution where I can combine MVC and an immutable dto
// I won't be using immutable dtos

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PublisherDto {

    @NotBlank(message = "Name can not be blank")
    String name;
    // This feature was removed during development period because of the complexity it requires
//    @Builder.Default
//    @NotEmpty(message = "Every publisher must have at least one book published")
//    Set<BookDto> books = new HashSet<>();

}
