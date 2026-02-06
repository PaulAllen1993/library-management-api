package com.contal.API.Service;

import com.contal.API.Exception.BookAlreadyReturnedException;
import com.contal.API.Exception.BookNotFoundException;
import com.contal.API.Model.Book;
import com.contal.API.Repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReturnBook {

    @Autowired
    private BookRepository bookRepository;

    public void checkIn(String isbn) throws BookNotFoundException, BookAlreadyReturnedException {
        Optional<Book> book = bookRepository.findById(isbn);
        if (!book.isPresent()) {
            throw new BookNotFoundException("Book with ISBN " + isbn + " not found");
        }
        if (book.get().isAvailable()) {
            throw new BookAlreadyReturnedException("Book with ISBN " + isbn + " is already available");
        }
        bookRepository.checkInBook(isbn);
    }
}
