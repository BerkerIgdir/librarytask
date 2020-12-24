package com.freesoft.task.bootstrap;


import com.freesoft.task.entities.*;
import com.freesoft.task.entities.enums.Roles;
import com.freesoft.task.mappers.PublisherMapper;
import com.freesoft.task.repositories.*;
import com.freesoft.task.services.AuthorService;
import com.freesoft.task.services.BookService;
import com.freesoft.task.services.PublisherService;
import com.freesoft.task.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.Arrays;


@Slf4j
@Component
@RequiredArgsConstructor
public class SqlSeeder implements CommandLineRunner {



    @Autowired
    UserService userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
    }

    void loadData(){

        Role role = Role.builder().role("ROLE_ADMIN").build();

        User user = User.builder().name("Berker").surname("Igdir").email("berkerigdir@gmail.com").roles(Arrays.asList(role)).password("1234").build();

        roleRepository.save(role);
        userRepository.save(user);

    }



}
