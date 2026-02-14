package com.library.API.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Book")
public class Book {

    @Id
    private String isbn;

    private String title;

    private String author;

    private boolean isAvailable;
}
