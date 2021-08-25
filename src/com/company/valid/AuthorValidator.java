package com.company.valid;

public class AuthorValidator {

    public boolean checkAuthor(String author) {
        return author.length() > 2;
    }

}
