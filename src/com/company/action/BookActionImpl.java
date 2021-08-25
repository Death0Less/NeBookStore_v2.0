package com.company.action;

import com.company.entity.Author;
import com.company.entity.Book;
import com.company.service.AuthorService;
import com.company.service.AuthorServiceImpl;
import com.company.service.BookService;
import com.company.service.BookServiceImpl;
import com.company.util.Reader;
import com.company.util.ReaderImpl;
import com.company.util.Writer;
import com.company.util.WriterImpl;
import com.company.valid.BookValidator;

import java.util.List;

public class BookActionImpl implements BookAction {

    private BookService bookService = new BookServiceImpl();
    private Reader reader = new ReaderImpl();
    private Writer writer = new WriterImpl();
    private BookValidator bookValidator = new BookValidator();
    private AuthorService authorService = new AuthorServiceImpl();

    public BookActionImpl(BookService bookService, Reader reader, Writer writer, BookValidator bookValidator, AuthorService authorService) {
        this.bookService = bookService;
        this.reader = reader;
        this.writer = writer;
        this.bookValidator = bookValidator;
        this.authorService = authorService;
    }

    public BookActionImpl() {
    }

    @Override
    public void addBook() {
        writer.writerStr("Введите автора:");
        Author author = getAuthorsInList();
        writer.writerStr("Введите название:");
        String title = reader.readStr();

        if (!checkBook(title)) {
            writer.writerStr("Вы ввели не те данные.");
            return;
        }

        writer.writerStr("Введите описание:");
        String description = reader.readStr();

        writer.writerStr("Введите цену:");
        double price = reader.readInt();

        if (!bookValidator.checkBook(title, price)) {
            writer.writerStr("Вы ввекли не ты данные.");
        }

        Book book = new Book(title, description, author, price);
        bookService.addBook(book);
    }

    @Override
    public void findById() {
        writer.writerStr("Введите айди:");
        int id = reader.readInt();
        Book book = bookService.findById(id);
        writer.writerStr(book.getId() + " " + book.getTitle());
    }

    @Override
    public void findByTitle() {
        writer.writerStr("Введите title:");
        String title = reader.readStr();
        Book book = bookService.findByTitle(title);
        if (book != null) {
            chooseBook(book);
            return;
        }
        writer.writerStr("Такой книги нет!");

    }

    @Override
    public void deleteById() {
        writer.writerStr("Введите id, чтобы удалить книгу:");
        int id = reader.readInt();
        bookService.deleteById(id);
    }

    @Override
    public void deleteByTitle() {
        writer.writerStr("Введите title, чтобы удалить книгу:");
        String title = reader.readStr();
        bookService.deleteByTitle(title);
    }

    @Override
    public void findByAll() {
        int count = 1;
        List<Book> bookList = bookService.findByAll();
        for (Book book : bookList) {
            writer.writerStr(count + book.getTitle());
            count++;
        }
    }

    @Override
    public void findByAuthorName() {
        writer.writerStr("Введите автора:");
        String authorName = reader.readStr();
        if (authorService.findByName(authorName) == null) {
            writer.writerStr("Такого автора нет!");
            return;
        }
        Author author = authorService.findByName(authorName);
        List<Book> byAuthorName = bookService.findByAuthorName(author);
        for (Book book : byAuthorName) {
            writer.writerStr(book.getId() + " " + book.getTitle());
        }
        writer.writerStr("Выберите книгу.");
        int i = reader.readInt();
        Book book = byAuthorName.get(i);
        chooseBook(book);
    }

    private Author getAuthorsInList() {
        List<Author> authorsList = authorService.findAll();
        for (Author author : authorsList) {
            writer.writerStr(author.getId() + " " + author.getNickname());
        }
        int i = reader.readInt();
        return authorsList.get(i);
    }

    //МБ В МОНОПОЛИЮ?


    private boolean checkBook(String title) {
        return bookService.findByTitle(title) == null;
    }

    private void chooseBook(Book book) {
        writer.writerStr("Название книги - " + book.getTitle());
        writer.writerStr("Автор книги - " + book.getAuthor().getNickname());
        writer.writerStr("Цена книги - " + book.getPrice());
        writer.writerStr("Описание книги - " + book.getDescription());
        writer.writerStr("0 - выйти.");
        writer.writerStr("1 - добавить книгу в корзину.");
        int x = reader.readInt();
        switch (x) {
            case 0:
                break;

            case 1:
                addBookToBasket(book);
                break;

            default:
                writer.writerStr("Вы ввели не те данные.");
        }
    }

    private void addBookToBasket(Book book) {
        Book[] books = MainActionImpl.activeSession.getBasket().getBooks();
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                books[i] = book;
                break;
            }
        }
    }

    public void deleteBookFromBasket() {
        Book[] books = MainActionImpl.activeSession.getBasket().getBooks();
        for (int i = 0; i < books.length; i++) {
            writer.writerStr(i + " " + books[i].getTitle());
        }
        writer.writerStr("Выберите книгу. которую хотите удалить.");
        int x = reader.readInt();
        if (books.length - 1 - x >= 0) System.arraycopy(books, x + 1, books, x, books.length - 1 - x);
    }

}
