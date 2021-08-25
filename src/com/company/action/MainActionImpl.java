package com.company.action;

import com.company.entity.Book;
import com.company.entity.Role;
import com.company.entity.Session;
import com.company.util.Reader;
import com.company.util.ReaderImpl;
import com.company.util.Writer;
import com.company.util.WriterImpl;

public class MainActionImpl implements MainAction {
    public static Session activeSession;

    private AddressAction addressAction = new AddressActionImpl();
    private AuthorAction authorAction = new AuthorActionImpl();
    private BookAction bookAction = new BookActionImpl();
    private OrderAction orderAction = new OrderActionImpl();
    private StoreAction storeAction = new StoreActionImpl();
    private CityAction cityAction = new CityActionImpl();
    private UserAction userAction = new UserActionImpl();
    private Writer writer = new WriterImpl();
    private Reader reader = new ReaderImpl();

    public static void main(String[] args) {
        MainAction mainAction = new MainActionImpl();
        mainAction.run();
    }

    @Override
    public void run() {
        choose();
    }

    private void choose() {
        while (true) {
            if (activeSession == null) {
                writer.writerStr("Привет, ёбик!");
            } else {
                writer.writerStr("Привет, " + activeSession.getUser().getLastName() + " " + activeSession.getUser().getFirstName());
            }
            showMenu();
            if (activeSession == null) {
                switch (reader.readInt()) {
                    case 0:
                        return;

                    case 1:
                        userAction.addUser();
                        continue;

                    case 2:
                        userAction.authorization();
                        continue;

                    case 3:
                        bookAction.findByTitle();
                        continue;

                    case 4:

                        bookAction.findByAuthorName();
                        continue;

                    default:
                        writer.writerStr("Такой операции нет.");
                }
            }
            if (activeSession.getUser().getRole().equals(Role.VISITOR)) {
                switch (reader.readInt()) {
                    case 0:
                        return;

                    case 1:
                        authorAction.findByName();
                        continue;

                    case 2:
                        authorAction.findAll();
                        continue;

                    case 3:
                        bookAction.findByTitle();
                        continue;

                    case 4:
                        bookAction.findByAuthorName();
                        continue;

                    case 5:
                        personalAccount();
                        switch (reader.readInt()) {
                            case 1:
                                userAction.updateFirstNme();
                                continue;

                            case 2:
                                userAction.updateLastName();
                                continue;

                            case 3:
                                userAction.updateEmail();
                                continue;

                            case 4:
                                userAction.updatePassword();
                                continue;

                            case 5:
                                orderHistoryMenu();
                                switch (reader.readInt()) {
                                    case 0:
                                        break;

                                    case 1:
                                        orderAction.orderHistory();

                                    default:
                                        writer.writerStr("Вы ввели не те данные.");
                                }
                                continue;

                            case 6:
                                basketMenu();
                                switch (reader.readInt()) {
                                    case 0:
                                        break;

                                    case 1:
                                        bookAction.deleteBookFromBasket();
                                        continue;

                                    default:
                                        writer.writerStr("Вы ввели не те данные.");
                                }
                                continue;

                            case 7:
                                orderMenu();
                                switch (reader.readInt()) {
                                    case 0:
                                        break;

                                    case 1:
                                        orderAction.addPickUp();
                                        continue;

                                    case 2:
                                        orderAction.addDelivery();
                                        continue;

                                    default:
                                        writer.writerStr("Вы ввели не те данные.");
                                }
                                continue;

                            case 8:
                                break;

                            default:
                                writer.writerStr("Такой операции нет.");
                        }
                        continue;
                    case 6:
                        logout();
                        continue;

                    default:
                        writer.writerStr("Такой операции нет.");
                }
            }
            if (activeSession.getUser().getRole().equals(Role.ADMINISTRATOR)) {
                switch (reader.readInt()) {
                    case 0:
                        return;

                    case 1:
                        cityAction.addCity();
                        continue;

                    case 2:
                        cityAction.deleteCity();
                        continue;

                    case 3:
                        addressAction.addAddress();
                        continue;

                    case 4:
                        addressAction.deleteAddress();
                        continue;

                    case 5:
                        authorAction.addAuthor();
                        continue;

                    case 6:
                        authorAction.deleteAuthor();
                        continue;

                    case 7:
                        bookAction.addBook();
                        continue;

                    case 8:
                        bookAction.deleteByTitle();
                        continue;

                    case 9:
                        bookAction.deleteById();
                        continue;

                    case 10:
                        personalAccount();
                        switch (reader.readInt()) {
                            case 1:
                                userAction.updateFirstNme();
                                continue;

                            case 2:
                                userAction.updateLastName();
                                continue;

                            case 3:
                                userAction.updateEmail();
                                continue;

                            case 4:
                                userAction.updatePassword();
                                continue;
                        }
                        continue;

                    case 11:
                        logout();
                        continue;

                    case 12:
                        cityAction.findAll();
                        continue;

                    case 13:
                        authorAction.findAll();
                        continue;

                    case 14:
                        storeAction.addStore();
                        continue;

                    case 15:
                        storeAction.deleteStore();
                        continue;

                    default:
                        writer.writerStr("Такой операции нет.");
                }
            }
        }
    }

    private void showMenu() {
        if (activeSession == null) {
            writer.writerStr("0 - выход из меню.");
            writer.writerStr("1 - регистрация.");
            writer.writerStr("2 - авторизация.");
            writer.writerStr("3 - поиск книг по названию.");
            writer.writerStr("4 - поиск книг по автору.");
        } else if (activeSession.getUser().getRole().equals(Role.VISITOR)) {
            writer.writerStr("0 - выход из меню.");
            writer.writerStr("1 - найти автора.");
            writer.writerStr("2 - показать всех авторов.");
            writer.writerStr("3 - найти книгу по названию.");
            writer.writerStr("4 - найти книги по автору.");
            writer.writerStr("5 - войти в личный кабинет.");
            writer.writerStr("6 - выход из сессии.");
        } else if (activeSession.getUser().getRole().equals(Role.ADMINISTRATOR)) {
            writer.writerStr("0 - выход из меню.");
            writer.writerStr("1 - добавление города");
            writer.writerStr("2 - удаление города.");
            writer.writerStr("3 - добавление адреса.");
            writer.writerStr("4 - удаление адреса.");
            writer.writerStr("5 - добавление автора.");
            writer.writerStr("6 - удаление автора.");
            writer.writerStr("7 - добавление книг.");
            writer.writerStr("8 - удаление книги по title.");
            writer.writerStr("9 - удаление книги по id.");
            writer.writerStr("10 - войти в личный кабинет.");
            writer.writerStr("11 - выход из сессии.");
            writer.writerStr("12 - вывести все города.");
            writer.writerStr("13 - вывести всех авторов.");
            writer.writerStr("14 - добавить магазин.");
            writer.writerStr("15 - удалить магазин.");
        }
    }

    private void logout() {
        activeSession = null;
    }

    private void personalAccount() {
        writer.writerStr(activeSession.getUser().getRole().toString());
        writer.writerStr("Имя - " + activeSession.getUser().getFirstName());
        writer.writerStr("Фамилия - " + activeSession.getUser().getLastName());
        writer.writerStr("Email - " + activeSession.getUser().getEmail());
        writer.writerStr("Выберите операцию:");
        writer.writerStr("1 - изменить имя.");
        writer.writerStr("2 - изменить фамилию.");
        writer.writerStr("3 - изменить email.");
        writer.writerStr("4 - изменить пароль.");
        writer.writerStr("5 - перейти в историю заказов.");
        if (activeSession.getUser().getRole().equals(Role.VISITOR)) {
            writer.writerStr("6 - перейти в корзину.");
        }
        if (activeSession.getBasket().getBooks().length >= 1) {
            writer.writerStr("7 - оформить заказ.");
        }
        writer.writerStr("8 - выход из личного кабинета.");
    }

    private void basketMenu() {
        Book[] books = activeSession.getBasket().getBooks();
        double price = 0;
        for (Book book : books) {
            if (book == null) break;
            writer.writerStr(book.getId() + " " + book.getTitle());
            price += book.getPrice();
        }
        writer.writerStr("Конечная сумма заказа - " + price);
        writer.writerStr("0 - выйти из меню.");
        writer.writerStr("1 - удалить книгу.");
    }

    private void orderMenu() {
        Book[] books = activeSession.getBasket().getBooks();
        double price = 0;
        for (Book book : books) {
            if (book == null) break;
            writer.writerStr(book.getId() + " " + book.getTitle());
            price += book.getPrice();
        }
        writer.writerStr("Конечная сумма заказа - " + price);
        writer.writerStr("0 - выход из меню.");
        writer.writerStr("1 - самовывоз.");
        writer.writerStr("2 - доставка.");
    }

    private void orderHistoryMenu() {
        writer.writerStr("0 - выход из меню.");
        writer.writerStr("1 - показать историю заказов.");
    }

}
