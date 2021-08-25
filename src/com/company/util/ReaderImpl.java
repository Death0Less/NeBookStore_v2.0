package com.company.util;

import java.util.Scanner;

public class ReaderImpl implements Reader {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public int readInt() {
        return scanner.nextInt();
    }

    @Override
    public String readStr() {
        return scanner.next();
    }
}
