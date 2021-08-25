package com.company.service;

import com.company.entity.User;
import com.company.repository.UserRepository;
import com.company.repository.database.DbUserRepository;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository = new DbUserRepository();

    @Override
    public void addUser(User user) {
        userRepository.addUser(user);
    }

    @Override
    public void delById(int id) {
        userRepository.delById(id);
    }

    @Override
    public void delByEmail(String email) {
        userRepository.delByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void updateEmail(int id, String email) {
        userRepository.updateEmail(id, email);
    }

    @Override
    public void updatePassword(int id, String password) {
        userRepository.updatePassword(id, password);
    }

    @Override
    public void updateFirstName(int id, String firstName) { /////////////
        userRepository.updateFirstName(id, firstName);
    }

    @Override
    public void updateLastName(int id, String lastName) { /////////////
        userRepository.updateLastName(id, lastName);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
