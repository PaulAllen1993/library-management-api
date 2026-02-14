package com.library.API.Service;

import java.util.List;

import com.library.API.Model.Book;

public interface BookService {
    Book addBook(Book book) throws Exception;

    void deleteBook(String isbn) throws Exception;

    void checkoutBook(String isbn) throws Exception;

    void returnBook(String isbn) throws Exception;

    List<Book> getAvailableBooks() throws Exception;
}
