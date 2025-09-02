package com.contal.API.Service;

import com.contal.API.Repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckoutBookImpl implements CheckoutBook {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void checkout(String isbn) {
        bookRepository.checkoutBook(isbn);
    }
}
