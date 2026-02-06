package com.contal.API.Service;

import com.contal.API.Exception.BookNotFoundException;
import com.contal.API.Model.Book;
import com.contal.API.Repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RemoveBook {

    @Autowired
    private BookRepository bookRepository;

    public void delete(String isbn) throws BookNotFoundException {
        Optional<Book> book = bookRepository.findById(isbn);
        if (!book.isPresent()) {
            throw new BookNotFoundException("Book with ISBN " + isbn + " not found");
        }
        bookRepository.deleteById(isbn);
    }
}
