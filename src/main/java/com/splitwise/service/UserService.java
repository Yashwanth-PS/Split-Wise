package com.splitwise.service;

import com.splitwise.model.User;

public interface UserService {
    public User getUser(Long userId) throws Exception;

    public User registerUser(User user);
}
