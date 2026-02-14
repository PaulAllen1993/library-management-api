package com.library.API.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
@Entity
@Table(name = "Book")
public class Book {

    @Id
    @NonNull
    private String isbn;

    private String title;

    private String author;

    private boolean isAvailable;
}
