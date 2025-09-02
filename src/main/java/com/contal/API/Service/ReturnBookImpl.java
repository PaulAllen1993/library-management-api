package com.contal.API.Service;

import com.contal.API.Repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReturnBookImpl implements ReturnBook {

    @Autowired
    BookRepository bookRepository;

    @Override
    public void checkIn(String isbn) {
        bookRepository.checkInBook(isbn);
    }
}
