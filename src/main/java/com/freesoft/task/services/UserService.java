package com.freesoft.task.services;

import com.freesoft.task.entities.User;

public interface UserService {

    User getUserByEmail(String email);
    void save(User user);
}
