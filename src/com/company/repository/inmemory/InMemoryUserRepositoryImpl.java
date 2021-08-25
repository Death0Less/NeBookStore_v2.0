package com.company.repository.inmemory;

import com.company.entity.User;
import com.company.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserRepositoryImpl implements UserRepository {

    private static int incId;
    private static InMemoryUserRepositoryImpl instance;
    private List<User> userList = new ArrayList<>();

    private InMemoryUserRepositoryImpl() {
    }

    public static InMemoryUserRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new InMemoryUserRepositoryImpl();
        }
        return instance;
    }


    @Override
    public void addUser(User user) {
        user.setId(incId++);
        userList.add(user);
    }

    @Override
    public void delById(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                userList.remove(user);
                break;
            }
        }
    }

    @Override
    public void delByEmail(String email) {
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                userList.remove(user);
                break;
            }
        }
    }

    @Override
    public List<User> findAll() {
        return userList;
    }

    @Override
    public void updateEmail(int id, String email) {
        for (User user : userList) {
            if (user.getId() == id) {
                user.setEmail(email);
                break;
            }
        }

    }

    @Override
    public void updatePassword(int id, String password) {
        for (User user : userList) {
            if (user.getId() == id) {
                user.setPassword(password);
                break;
            }
        }

    }

    @Override
    public void updateFirstName(int id, String firstName) { /////////////
        for (User user : userList) {
            if (user.getId() == id) {
                user.setFirstName(firstName);
                break;
            }
        }
    }

    @Override
    public void updateLastName(int id, String lastName) { /////////////
        for (User user : userList) {
            if (user.getId() == id) {
                user.setLastName(lastName);
                break;
            }
        }
    }

    @Override
    public User findById(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }


    @Override
    public User findByEmail(String email) {
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}
