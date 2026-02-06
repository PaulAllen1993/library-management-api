package com.contal.API.Service;

import com.contal.API.Exception.DuplicateISBNException;
import com.contal.API.Model.Book;
import com.contal.API.Repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddBook {

    @Autowired
    private BookRepository bookRepository;

    public Book add(Book book) throws DuplicateISBNException {
        Optional<Book> existingBook = bookRepository.findById(book.getIsbn());
        if (existingBook.isPresent()) {
            throw new DuplicateISBNException("Book with ISBN " + book.getIsbn() + " already exists");
        }
        book.setAvailable(true);
        return bookRepository.save(book);
    }
}
