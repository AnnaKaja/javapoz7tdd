package com.sda.services.bookstore;

import java.util.ArrayList;
import java.util.List;

public class Bookstore {

    private final List<Book> books;

    public Bookstore() {
        this.books = new ArrayList<>();

    }

    public void add(Book book) {
        books.add(book);
    }

    public void updateTitle(Book book, String title) {
        Book bookInBookstore = books.get(books.indexOf(book));
        bookInBookstore.setTitle(title);

    }

    public List<Book> getBooks() {
        return books;
    }


}
