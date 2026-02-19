package com.library.API.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.library.API.Model.Book;
import com.library.API.Service.*;

import io.micrometer.common.lang.NonNull;

import java.util.List;

@RestController
@RequestMapping("/books")
public class LibraryController {

    @Autowired
    private BookService bookService;

    public LibraryController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public Book addBook(@RequestBody @NonNull Book book) throws Exception {
        return bookService.addBook(book);
    }

    @DeleteMapping("/{isbn}")
    public void removeBook(@RequestBody @NonNull String isbn) throws Exception {
        bookService.deleteBook(isbn);
    }

    @PutMapping("/{isbn}/checkout")
    public void checkoutBook(@RequestBody @NonNull String isbn) throws Exception {
        bookService.checkoutBook(isbn);
    }

    @PutMapping("/{isbn}/return")
    public void returnBook(@RequestBody @NonNull String isbn) throws Exception {
        bookService.returnBook(isbn);
    }

    @GetMapping("/{books}/available")
    public List<Book> availableBooks(@RequestBody @NonNull String isAvailable) throws Exception {
        return bookService.getAvailableBooks();
    }

}
