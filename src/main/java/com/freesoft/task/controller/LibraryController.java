package com.freesoft.task.controller;

import com.freesoft.task.entities.Book;
import com.freesoft.task.repositories.BookRepository;
import com.freesoft.task.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LibraryController {

    @Autowired
    private final BookService bookService;


    @GetMapping("")
    String redirectMenu(Model model){

        return "redirect:/main";
    }

    @GetMapping("/main")
    String mainMenu(Model model){

        return "book-main";
    }


    @GetMapping("books/list")
    String bookList(Model model,
                    @RequestParam("page") Optional<Integer> page,
                    @RequestParam("size") Optional<Integer> size)
    {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Book> books = bookService.getAllBooks(PageRequest.of(currentPage - 1,pageSize));

        model.addAttribute("books",books);

        if(books.getTotalPages() >1){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, books.getTotalPages())
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "book-list";
    }

    @PostMapping("books/searchByName")
    String bookNameList(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size,
                        @RequestParam(defaultValue = "name") String name){

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Book> books = bookService.getBooksByName(name,PageRequest.of(currentPage-1,pageSize));

        model.addAttribute("books",books);

        if(books.getTotalPages() > 1){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, books.getTotalPages())
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "book-list";
    }

    @PostMapping("books/searchByAuthor")
    String bookAuthorList(Model model,
                          @RequestParam("page") Optional<Integer> page,
                          @RequestParam("size") Optional<Integer> size,
                          @RequestParam(defaultValue = "author") String author)
    {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);


        Page<Book> books = bookService.getBooksByAuthor(author,PageRequest.of(currentPage-1,pageSize));

        model.addAttribute("books",books);

        if(books.getTotalPages() > 1){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, books.getTotalPages())
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "book-list";
    }


    @GetMapping("books/bookForm")
    String showForm(Model model){

        Book book = new Book();

        model.addAttribute("book",book);

        return "book-form";
    }

    @GetMapping("books/queryForm")
    String queryForm(Model model){

        Book book = new Book();

        model.addAttribute("book",book);

        return "book-query";
    }


    @GetMapping("books/bookFormForUpdate")
    String updateForm(@RequestParam("bookId") Long id, Model model){

        Book book = bookService.getBookById(id);

        model.addAttribute("book",book);

        return "book-form";
    }

    @PostMapping("books/save")
    String bookSave(@Valid  @ModelAttribute("book") Book book){

        bookService.save(book);

        return "redirect:/books/list";
    }

    @GetMapping("books/delete")
    String bookdelete(@RequestParam("bookId") Long id){

        Book book = bookService.getBookById(id);

        bookService.delete(book);

        return "redirect:/books/list";
    }

}
