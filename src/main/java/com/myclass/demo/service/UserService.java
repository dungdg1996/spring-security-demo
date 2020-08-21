package com.myclass.demo.service;

import com.myclass.demo.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(int id);

    void save(User user);

    void delete(int id);
}
