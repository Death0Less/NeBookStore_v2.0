package com.company.valid;

public class BookValidator {
    public boolean checkBook(String title, double price) {
        return title.length() > 3 && price > 0;
    }
}
