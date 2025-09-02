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
    private AddBook addBook;
    private RemoveBook removeBook;
    private CheckoutBook checkoutBook;
    private ReturnBook returnBook;
    private CheckAvailable checkAvailable;

    @PostMapping("/book")
    public Book addBook(@RequestBody Book book) throws Exception{
        return addBook.add(book);
    }

    @DeleteMapping("/{isbn}")
    public void removeBook(@RequestBody String isbn) throws Exception{
        removeBook.delete(isbn);
    }

    @PutMapping("/{isbn}/checkout")
    public void checkoutBook(@RequestBody String isbn) throws Exception{
        checkoutBook.checkout(isbn);
    }

    @PutMapping("/{isbn}/return")
    public void returnBook(@RequestBody String isbn) throws Exception{
        returnBook.checkIn(isbn);
    }

    @GetMapping("/{books}/available")
    public List<Book> availableBooks(@RequestBody String isAvailable) throws Exception{
        return checkAvailable.available(isAvailable);
    }

}
