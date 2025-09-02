package com.contal.API.Service;

import com.contal.API.Model.Book;
import com.contal.API.Repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddBookImpl implements AddBook {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book add(Book book) {
        return bookRepository.save(book);
    }
}
