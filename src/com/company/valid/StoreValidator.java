package com.company.valid;

public class StoreValidator {
    public boolean validStoreName(String storeName) {
        return storeName.length() > 2;
    }
}
