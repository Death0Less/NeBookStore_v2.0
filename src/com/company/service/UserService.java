package com.company.service;

import com.company.entity.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    void delById(int id);

    void delByEmail(String email);

    List<User> findAll();

    void updateEmail(int id, String email);

    void updatePassword(int id, String password);

    void updateFirstName(int id, String firstName); //////

    void updateLastName(int id, String lastName); //////

    User findById(int id);

    User findByEmail(String email);
}
