package com.company.repository.file;

import java.util.List;

public interface DataBase {
    <T> void write(List<T> list, Class<T> clazz);

    <T> List<T> read(Class<T> clazz);

    int getId(Class<?> clazz);

    void setId(Class<?> clazz, int id);
}
