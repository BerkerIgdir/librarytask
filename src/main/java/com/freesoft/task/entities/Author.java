package com.freesoft.task.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"name", "surname"})
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Author extends BaseEntity {

    @Column(length = 36, columnDefinition = "varchar(36)",  nullable = false)
    String name;
    @Column(length = 36, columnDefinition = "varchar(36)",nullable = false )
    String surname;

    @OneToMany(mappedBy = "author")
    Set<Book> books = new HashSet<>();
}
