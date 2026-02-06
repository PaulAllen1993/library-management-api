package com.contal.API.Service;

import com.contal.API.Exception.BookAlreadyReturnedException;
import com.contal.API.Exception.BookNotAvailableException;
import com.contal.API.Exception.BookNotFoundException;
import com.contal.API.Exception.DuplicateISBNException;
import com.contal.API.Model.Book;
import com.contal.API.Repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book addBook(Book book) throws DuplicateISBNException {
        Optional<Book> existingBook = bookRepository.findById(book.getIsbn());
        if (existingBook.isPresent()) {
            throw new DuplicateISBNException("Book with ISBN " + book.getIsbn() + " already exists");
        }
        book.setAvailable(true);
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(String isbn) throws BookNotFoundException {
        Optional<Book> book = bookRepository.findById(isbn);
        if (!book.isPresent()) {
            throw new BookNotFoundException("Book with ISBN " + isbn + " not found");
        }
        bookRepository.deleteById(isbn);
    }

    @Override
    public void checkoutBook(String isbn) throws BookNotFoundException, BookNotAvailableException {
        Optional<Book> book = bookRepository.findById(isbn);
        if (!book.isPresent()) {
            throw new BookNotFoundException("Book with ISBN " + isbn + " not found");
        }
        if (!book.get().isAvailable()) {
            throw new BookNotAvailableException("Book with ISBN " + isbn + " is not available");
        }
        bookRepository.checkoutBook(isbn);
    }

    @Override
    public void returnBook(String isbn) throws BookNotFoundException, BookAlreadyReturnedException {
        Optional<Book> book = bookRepository.findById(isbn);
        if (!book.isPresent()) {
            throw new BookNotFoundException("Book with ISBN " + isbn + " not found");
        }
        if (book.get().isAvailable()) {
            throw new BookAlreadyReturnedException("Book with ISBN " + isbn + " is already available");
        }
        bookRepository.checkInBook(isbn);
    }

    @Override
    public List<Book> getAvailableBooks() throws Exception {
        return bookRepository.isAvailable();
    }
}
