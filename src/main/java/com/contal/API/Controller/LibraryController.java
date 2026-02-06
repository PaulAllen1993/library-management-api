package com.contal.API.Controller;

import com.contal.API.Model.Book;
import com.contal.API.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class LibraryController {

    @Autowired
    private BookService bookService;

    @PostMapping("/book")
    public Book addBook(@RequestBody Book book) throws Exception {
        return bookService.addBook(book);
    }

    @DeleteMapping("/{isbn}")
    public void removeBook(@PathVariable String isbn) throws Exception {
        bookService.deleteBook(isbn);
    }

    @PutMapping("/{isbn}/checkout")
    public void checkoutBook(@PathVariable String isbn) throws Exception {
        bookService.checkoutBook(isbn);
    }

    @PutMapping("/{isbn}/return")
    public void returnBook(@PathVariable String isbn) throws Exception {
        bookService.returnBook(isbn);
    }

    @GetMapping("/available")
    public List<Book> availableBooks() throws Exception {
        return bookService.getAvailableBooks();
    }

}
