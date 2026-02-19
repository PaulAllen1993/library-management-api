package com.library.API.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.library.API.Exception.BookAlreadyReturnedException;
import com.library.API.Exception.BookNotAvailableException;
import com.library.API.Exception.BookNotFoundException;
import com.library.API.Exception.DuplicateISBNException;
import com.library.API.Model.Book;
import com.library.API.Repo.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(@NonNull Book book) throws DuplicateISBNException {
        if (bookRepository.findById(book.getIsbn()).isPresent()) {
            throw new DuplicateISBNException("Book with ISBN " + book.getIsbn() + " already exists");
        }
        book.setAvailable(true);
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(@NonNull String isbn) throws BookNotFoundException {
        bookRepository.findById(isbn)
                .orElseThrow(() -> new BookNotFoundException("Book with ISBN " + isbn + " not found"));
        bookRepository.deleteById(isbn);
    }

    @Override
    public void checkoutBook(@NonNull String isbn) throws BookNotFoundException, BookNotAvailableException {
        Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new BookNotFoundException("Book with ISBN " + isbn + " not found"));

        if (!book.isAvailable()) {
            throw new BookNotAvailableException("Book with ISBN " + isbn + " is not available");
        }
        bookRepository.checkoutBook(isbn);
    }

    @Override
    public void returnBook(@NonNull String isbn) throws BookNotFoundException, BookAlreadyReturnedException {
        Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new BookNotFoundException("Book with ISBN " + isbn + " not found"));

        if (book.isAvailable()) {
            throw new BookAlreadyReturnedException("Book with ISBN " + isbn + " is already available");
        }
        bookRepository.checkInBook(isbn);
    }

    @Override
    public List<Book> getAvailableBooks() throws Exception {
        return bookRepository.isAvailable();
    }
}
