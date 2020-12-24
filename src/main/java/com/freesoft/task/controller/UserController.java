package com.freesoft.task.controller;

import com.freesoft.task.entities.Book;
import com.freesoft.task.entities.User;
import com.freesoft.task.services.RoleService;
import com.freesoft.task.services.UserService;
import lombok.RequiredArgsConstructor;
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

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final RoleService roleService;

    @GetMapping("/form")
    String userList(Model model){

        model.addAttribute("user",new User());

        return "user-form";
    }

    @PostMapping("/save")
    String userSave(@Valid @ModelAttribute("user") User user){

        userService.save(user);

        return "redirect:/main";
    }

    @GetMapping("/list")
    String userSave(Model model,
                    @RequestParam("page") Optional<Integer> page,
                    @RequestParam("size") Optional<Integer> size){

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<User> users = userService.getAllUsers(PageRequest.of(currentPage - 1,pageSize));

        model.addAttribute("users",users);

        if(users.getTotalPages() >1){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, users.getTotalPages())
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }


        return "user-list";
    }


}
