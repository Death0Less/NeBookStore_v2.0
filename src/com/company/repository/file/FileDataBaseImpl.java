package com.company.repository.file;

import com.company.entity.*;

import java.awt.print.Book;
import java.io.*;
import java.util.List;

public class FileDataBaseImpl implements DataBase {

    private static final String FILE_NAME = "FileName.txt";
    private static final File FILE = new File(FILE_NAME);
    private static Data data = new Data();

    { // Статический блок(инициализация), запустится первым при запуске программы
        if (FILE.exists() && FILE.length() != 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
                data = (Data) ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FILE.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            data = new Data();
        }
    }

    private void writeData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> void write(List<T> list, Class<T> clazz) {
        switch (clazz.getSimpleName()) { // Получаем имя класса
            case "Address":
                data.setAddressList((List<Address>) list);
                writeData();
                break;

            case "Author":
                data.setAuthorList((List<Author>) list);
                writeData();
                break;

            case "Basket":
                data.setBasketList((List<Basket>) list);
                writeData();
                break;

            case "Book":
                data.setBookList((List<Book>) list);
                writeData();
                break;

            case "City":
                data.setCityList((List<City>) list);
                writeData();
                break;

            case "Order":
                data.setOrderList((List<Order>) list);
                writeData();
                break;

            case "Store":
                data.setStoreList((List<Store>) list);
                writeData();
                break;

            case "User":
                data.setUserList((List<User>) list);
                writeData();
                break;
        }
    }

    @Override
    public <T> List<T> read(Class<T> clazz) {
        switch (clazz.getSimpleName()) {
            case "Address":
                return (List<T>) data.getAddressList();

            case "Author":
                return (List<T>) data.getAuthorList();

            case "Basket":
                return (List<T>) data.getBasketList();

            case "Book":
                return (List<T>) data.getBookList();

            case "City":
                return (List<T>) data.getCityList();

            case "Order":
                return (List<T>) data.getOrderList();

            case "Store":
                return (List<T>) data.getStoreList();

            case "User":
                return (List<T>) data.getUserList();
        }
        return null;
    }

    @Override
    public int getId(Class<?> clazz) {
        switch (clazz.getSimpleName()) {
            case "Address":
                return data.getAddressId();

            case "Author":
                return data.getAuthorId();

            case "Basket":
                return data.getBasketId();

            case "Book":
                return data.getBookId();

            case "City":
                return data.getCityId();

            case "Order":
                return data.getOrderId();

            case "Store":
                return data.getStoreId();

            case "User":
                return data.getUserId();
        }
        return 0;
    }

    @Override
    public void setId(Class<?> clazz, int id) {
        switch (clazz.getSimpleName()) {
            case "Address":
                data.setAddressId(id);
                writeData();
                break;

            case "Author":
                data.setAuthorId(id);
                writeData();
                break;

            case "Basket":
                data.setBasketId(id);
                writeData();
                break;

            case "Book":
                data.setBookId(id);
                writeData();
                break;

            case "City":
                data.setCityId(id);
                writeData();
                break;

            case "Order":
                data.setOrderId(id);
                writeData();
                break;

            case "Store":
                data.setStoreId(id);
                writeData();
                break;

            case "User":
                data.setUserId(id);
                writeData();
                break;
        }
    }
}
