package com.freesoft.task.services;

import com.freesoft.task.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {

    User getUserByEmail(String email);
    void save(User user);
    Page<User> getAllUsers(Pageable pageable);
}
