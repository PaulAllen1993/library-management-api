package com.contal.API.Service;

import com.contal.API.Repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoveBookImpl implements RemoveBook {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void delete(String isbn) {
        bookRepository.deleteById(isbn);
    }
}
