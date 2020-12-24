package com.freesoft.task.entities;


import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.*;


@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Publisher extends BaseEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long id;

    @Column(length = 36, columnDefinition = "varchar(36)",  nullable = false)
    String name;


    @Builder.Default
    @OneToMany(mappedBy = "publisher",fetch = FetchType.EAGER)
    List<Book> books = new ArrayList<>();

   public void addBook(Book book){

       Optional<Book> bookToUpdate = books.stream().filter(book1 -> book1.getId().equals(book.getId())).findFirst();

       if( bookToUpdate.isPresent()){
           int index = books.indexOf(bookToUpdate.get());
           books.set(index,book);
       }
       else {
           books.add(book);
       }
        books.add(book);
        book.setPublisher(this);
    }

}