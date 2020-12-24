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
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class LibraryController {

    @Autowired
    private final BookService bookService;


    @GetMapping("/")
    String mainMenu(Model model){

        return "redirect:/books/list";
    }

    @GetMapping("/list")
    String bookList(Model model,@RequestParam(defaultValue = "0") Integer pageNo,
                    @RequestParam(defaultValue = "10") Integer pageSize){

        List<Book> books = bookService.getAllBooks(PageRequest.of(pageNo,pageSize)).stream().collect(Collectors.toList());



        model.addAttribute("books",books);

        return "book-list";
    }

    @RequestMapping("/bookForm")
    String showForm(Model model){

        Book book = new Book();

        model.addAttribute("book",book);

        return "book-form";
    }


    @GetMapping("/bookFormForUpdate")
    String updateForm(@RequestParam("bookId") Long id, Model model){

        Book book = bookService.getBookById(id);

        model.addAttribute("book",book);

        return "book-form";
    }

    @PostMapping("/save")
    String bookSave(@Valid  @ModelAttribute("book") Book book){

        bookService.save(book);

        return "redirect:/books/list";
    }

    @GetMapping("/delete")
    String bookdelete(@RequestParam("bookId") Long id){

        Book book = bookService.getBookById(id);

        bookService.delete(book);

        return "redirect:/books/list";
    }

}
