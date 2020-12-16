package com.freesoft.task.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book extends BaseEntity{

    @Column(length = 36, columnDefinition = "varchar(36)",  nullable = false,unique = true)
    String name;
    @Column(length = 36, columnDefinition = "varchar(36)" )
    String secondaryName;
    @Column(length = 36, columnDefinition = "varchar(36)")
    String series;
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false,unique = true)
    String isbn;
    @Column(length = 150, columnDefinition = "varchar(150)", nullable = false)
    String description;
    @Column
    int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    Author author;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    Publisher  publisher;

}
