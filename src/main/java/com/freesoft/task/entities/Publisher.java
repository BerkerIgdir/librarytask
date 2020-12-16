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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Publisher extends BaseEntity {

    @Column(length = 36, columnDefinition = "varchar(36)",  nullable = false,unique = true)
    String name;

    @OneToMany(mappedBy = "publisher")
    Set<Book> books = new HashSet<>();
}