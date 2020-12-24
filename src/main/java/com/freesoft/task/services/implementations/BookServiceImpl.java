package com.freesoft.task.services.implementations;


import com.freesoft.task.entities.Author;
import com.freesoft.task.entities.Book;
import com.freesoft.task.entities.Publisher;
import com.freesoft.task.repositories.AuthorRepository;
import com.freesoft.task.repositories.BookRepository;
import com.freesoft.task.repositories.PublisherRepository;
import com.freesoft.task.services.BookService;
import com.freesoft.task.services.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final AuthorRepository authorRepository;

    @Autowired
    private final PublisherRepository publisherRepository;


    @Override
    public Page<Book> getAllBooks(Pageable pageable) {

        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> getBooksByAuthor(String author, Pageable pageable) {

        return bookRepository.findByAuthor(author,pageable);
    }

    @Override
    public Page<Book> getBooksByPublisher(String publisher, Pageable pageable) {

        return bookRepository.findByPublisher(publisher,pageable);
    }

    @Override
    public Page<Book> getBooksByPublisherAndAuthor(String publisher, String author, Pageable pageable) {

        return bookRepository.findByPublisherAndAuthor(publisher,author,pageable);

    }

    @Override
    public Page<Book> getBooksByName(String name,Pageable pageable) {


        return bookRepository.findByName(name,pageable);

    }


    @Override
    public Book getBookByISBN(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(BookNotFoundException::new);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    @Override
    public void save(Book book) {

       Author author = authorRepository.findByNameAndSurname(book.getAuthor().getName(),book.getAuthor().getSurname())
               .orElseGet(() ->Author.builder().name(book.getAuthor().getName()).surname(book.getAuthor().getSurname()).build());

       Publisher publisher = publisherRepository.findByName(book.getPublisher().getName())
               .orElseGet(() ->Publisher.builder().name(book.getPublisher().getName()).build());

       author.addBooks(book);
       publisher.addBook(book);

       publisherRepository.save(publisher);
       authorRepository.save(author);
    }

    @Override
    public void delete(Book book) {
        Author author = authorRepository.findByNameAndSurname(book.getAuthor().getName(),book.getAuthor().getSurname()).get();
        author.getBooks().remove(book);
        Publisher publisher = publisherRepository.findByName(book.getPublisher().getName()).get() ;
        publisher.getBooks().remove(book);

        bookRepository.delete(book);
        publisherRepository.save(publisher);
        authorRepository.save(author);
    }
}
