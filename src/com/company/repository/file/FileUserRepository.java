package com.company.repository.file;

import com.company.entity.User;
import com.company.repository.UserRepository;

import java.util.List;

public class FileUserRepository implements UserRepository {

    private DataBase dataBase = new FileDataBaseImpl();
    private List<User> temp;

    {
        temp = dataBase.read(User.class);
    }


    @Override
    public void addUser(User user) {
        int lastUserId = dataBase.getId(User.class);
        user.setId(++lastUserId);
        dataBase.setId(User.class, lastUserId);
        temp.add(user);
        dataBase.write(temp, User.class);
    }

    @Override
    public void delById(int id) {
        for (User user : temp) {
            if (user.getId() == id) {
                temp.remove(user);
                break;
            }
        }
    }

    @Override
    public void delByEmail(String email) {
        for (User user : temp) {
            if (user.getEmail().equals(email)) {
                temp.remove(user);
                break;
            }
        }
    }

    @Override
    public List<User> findAll() {
        return temp;
    }

    @Override
    public void updateEmail(int id, String email) {
        for (User user : temp) {
            if (user.getId() == id) {
                user.setEmail(email);
                break;
            }
        }
    }

    @Override
    public void updatePassword(int id, String password) {
        for (User user : temp) {
            if (user.getId() == id) {
                user.setPassword(password);
                break;
            }
        }
    }

    @Override
    public void updateFirstName(int id, String firstName) {
        for (User user : temp) {
            if (user.getId() == id) {
                user.setFirstName(firstName);
                break;
            }
        }
    }

    @Override
    public void updateLastName(int id, String lastName) {
        for (User user : temp) {
            if (user.getId() == id) {
                user.setLastName(lastName);
                break;
            }
        }
    }

    @Override
    public User findById(int id) {
        for (User user : temp) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        for (User user : temp) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}
