package com.freesoft.task.services.implementations;

import com.freesoft.task.entities.User;
import com.freesoft.task.repositories.UserRepository;
import com.freesoft.task.services.RoleService;
import com.freesoft.task.services.UserService;
import com.freesoft.task.services.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleService roleService;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder = serviceEncoder();

    @Bean
    public BCryptPasswordEncoder serviceEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(BookNotFoundException::new);
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.getRoles().get(0).getUsers().add(user);
        roleService.save(user.getRoles().get(0));
        userRepository.save(user);
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
