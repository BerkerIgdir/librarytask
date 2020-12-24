package com.freesoft.task.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"name", "surname"})
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Author extends BaseEntity{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long id;

    @Column(length = 36, columnDefinition = "varchar(36)",  nullable = false)
    String name;
    @Column(length = 36, columnDefinition = "varchar(36)",nullable = false )
    String surname;

    @Builder.Default
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Book> books = new ArrayList<>();



   public void addBooks(Book book){

      Optional<Book> bookToUpdate = books.stream().filter(book1 -> book1.getId().equals(book.getId())).findFirst();

       if( bookToUpdate.isPresent()){
           int index = books.indexOf(bookToUpdate.get());
           books.set(index,book);
       }
        else {
           books.add(book);
       }
        book.setAuthor(this);
   }

}

