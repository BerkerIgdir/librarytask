package com.freesoft.task.services.implementations;

import com.freesoft.task.dtos.BookDto;
import com.freesoft.task.mappers.BookMapper;
import com.freesoft.task.repositories.BookRepository;
import com.freesoft.task.services.BookService;
import com.freesoft.task.services.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    @Autowired
   private final BookMapper bookMapper;
    @Autowired
   private final BookRepository bookRepository;

    @Override
    public Page<BookDto> getAllBooks(Pageable pageable) {

        List<BookDto> bookDtoList = bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(bookDtoList,pageable,bookDtoList.size());
    }

    @Override
    public Page<BookDto> getBooksByAuthor(String author, Pageable pageable) {

        List<BookDto> bookDtoList = bookRepository.findByAuthor(author)
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(bookDtoList,pageable,bookDtoList.size());
    }

    @Override
    public Page<BookDto> getBooksByPublisher(String publisher, Pageable pageable) {

        List<BookDto> bookDtoList = bookRepository.findByPublisher(publisher)
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(bookDtoList,pageable,bookDtoList.size());
    }

    @Override
    public Page<BookDto> getBooksByPublisherAndAuthor(String publisher, String author, Pageable pageable) {

        List<BookDto> bookDtoList = bookRepository.findByPublisherAndAuthor(publisher,author)
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(bookDtoList,pageable,bookDtoList.size());

    }

    @Override
    public BookDto getBookByName(String name) {
        return bookMapper.toDto(bookRepository.findByName(name).orElseThrow(BookNotFoundException::new));
    }

    @Override
    public BookDto getBookByISBN(String isbn) {
        return bookMapper.toDto(bookRepository.findByIsbn(isbn).orElseThrow(BookNotFoundException::new));
    }
}
