package com.contal.API.Service;

import com.contal.API.Model.Book;

import java.util.List;

public interface BookService {
    Book addBook(Book book) throws Exception;

    void deleteBook(String isbn) throws Exception;

    void checkoutBook(String isbn) throws Exception;

    void returnBook(String isbn) throws Exception;

    List<Book> getAvailableBooks() throws Exception;
}
