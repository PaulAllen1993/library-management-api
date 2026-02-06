package com.contal.API.Service;

import com.contal.API.Model.Book;
import com.contal.API.Repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckAvailable {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> available(String isAvailable) throws Exception {
        return bookRepository.isAvailable();
    }
}
