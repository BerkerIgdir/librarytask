package com.freesoft.task.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book extends BaseEntity{
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    long id;

    @Column(length = 36, columnDefinition = "varchar(36)",  nullable = false)
    String name;
    @Column(length = 36, columnDefinition = "varchar(36)" )
    String secondaryName;
    @Column(length = 36, columnDefinition = "varchar(36)")
    String series;
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false,unique = true)
    String isbn;
    @Column(length = 150, columnDefinition = "varchar(150)", nullable = false)
    String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id",nullable = false)
    Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id",nullable = false)
    Publisher  publisher;

}
