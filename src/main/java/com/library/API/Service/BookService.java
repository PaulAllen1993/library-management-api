package com.library.API.Service;

import java.util.List;

import org.springframework.lang.NonNull;
import com.library.API.Model.Book;

public interface BookService {
    Book addBook(@NonNull Book book) throws Exception;

    void deleteBook(@NonNull String isbn) throws Exception;

    void checkoutBook(@NonNull String isbn) throws Exception;

    void returnBook(@NonNull String isbn) throws Exception;

    List<Book> getAvailableBooks() throws Exception;
}
