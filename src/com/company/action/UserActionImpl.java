package com.company.action;

import com.company.entity.*;
import com.company.service.UserService;
import com.company.service.UserServiceImpl;
import com.company.util.Reader;
import com.company.util.ReaderImpl;
import com.company.util.Writer;
import com.company.util.WriterImpl;
import com.company.valid.UserValidator;

import java.util.List;

public class UserActionImpl implements UserAction {

    private Reader reader = new ReaderImpl();
    private Writer writer = new WriterImpl();
    private UserService userService = new UserServiceImpl();
    private UserValidator userValidator = new UserValidator();

    public UserActionImpl(Reader reader, Writer writer, UserService userService, UserValidator userValidator) {
        this.reader = reader;
        this.writer = writer;
        this.userService = userService;
        this.userValidator = userValidator;
    }

    public UserActionImpl() {
    }

    @Override
    public void addUser() {
        writer.writerStr("Введите имя пользователя:");
        String firstName = reader.readStr();
        if (!userValidator.checkFirstName(firstName)) {
            writer.writerStr("Вы ввели не те данные.");
            return;
        }
        writer.writerStr("Введите фамилию пользователя:");
        String lastName = reader.readStr();
        if (!userValidator.checkLastName(lastName)) {
            writer.writerStr("Вы ввели не те данные");
            return;
        }
        writer.writerStr("Введите email:");
        String email = reader.readStr();
        if (!userValidator.checkEmail(email)) {
            writer.writerStr("Вы ввели не те данные");
            return;
        }

        writer.writerStr("Введите пароль:");
        String password = reader.readStr();
        if (!userValidator.checkPassword(password)) {
            writer.writerStr("Вы ввели не те данные");
            return;
        }

        User user = new User(lastName, firstName, email, password, Role.VISITOR);
        userService.addUser(user);

        if (user.getEmail().equals("admin@chmoshnik.com")) {
            user.setRole(Role.ADMINISTRATOR);
        }
    }

    @Override
    public void delById() {
        writer.writerStr("Введите id, чтобы удалить user:");
        int id = reader.readInt();
        userService.delById(id);
    }

    @Override
    public void delByEmail() {
        writer.writerStr("Введите email, чтобы удалить user:");
        String email = reader.readStr();
        userService.delByEmail(email);
    }

    @Override
    public void findAll() {
        List<User> users = userService.findAll();
        for (User user : users) {
            writer.writerStr(user.getLastName() + " " + user.getFirstName());
        }
    }

    @Override
    public void updateEmail() {
        writer.writerStr("Введите id, чтобы обновить email:");
        int id = reader.readInt();
        writer.writerStr("Введите новый email:");
        String email = reader.readStr();
        userService.updateEmail(id, email);
    }

    @Override
    public void updatePassword() {
        writer.writerStr("Введите id, чтобы обновить password:");
        int id = reader.readInt();
        writer.writerStr("Введите новый password:");
        String password = reader.readStr();
        userService.updatePassword(id, password);
    }

    @Override
    public void updateFirstNme() { ////////////////
        writer.writerStr("Введите id, чтобы изменить имя:");
        int id = reader.readInt();
        writer.writerStr("Введите новое имя:");
        String firstName = reader.readStr();
        userService.updateFirstName(id, firstName);
    }

    @Override
    public void updateLastName() {
        writer.writerStr("Введите id, чтобы изменить фамилию:");
        int id = reader.readInt();
        writer.writerStr("Введите новую фамилию:");
        String lastName = reader.readStr();
        userService.updateLastName(id, lastName);
    }

    @Override
    public void findById() {
        writer.writerStr("Введите id, чтобы найти user");
        int id = reader.readInt();
        User user = userService.findById(id);
        writer.writerStr(user.getId() + " " + user.getFirstName() + " " + user.getLastName());
    }

    @Override
    public void findByEmail() {
        writer.writerStr("Введите email, чтобы найти user:");
        String email = reader.readStr();
        User user = userService.findByEmail(email);
        writer.writerStr(user.getId() + " " + user.getFirstName() + " " + user.getLastName());
    }

    /////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////
    /////ДОБРОЕ УТРО, ЧМОШНИК))0))0)/////////////////////////////
    /////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////
    ///////////КУШАЙЦЕ, НЕ ОБЛЯПАЙТЕС////////////////////////////
    /////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////
    //////////ХОЧУ ШАВУХУ И АРТЕМА МЕЛЕЩЕВА!!!!1!!///////////////
    /////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////


    @Override
    public void authorization() {
        writer.writerStr("Введите email:");
        String email = reader.readStr();
        writer.writerStr("Введите password:");
        String password = reader.readStr();

        User user = userService.findByEmail(email);
        if (user == null) {
            writer.writerStr("Пользователя с таким email не существует");
            return;
        }

        if (user.getPassword().equals(password)) {
            MainActionImpl.activeSession = new Session();
            MainActionImpl.activeSession.setUser(user);
            MainActionImpl.activeSession.setBasket(new Basket(new Book[30]));
        }
    }
}
