package com.contal.API.Repo;

import com.contal.API.Model.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    @Query("SELECT b FROM Book b WHERE b.isAvailable = true")
    List<Book> isAvailable();

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.isAvailable = false WHERE b.isbn = :isbn")
    void checkoutBook(@Param("isbn") String isbn);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.isAvailable = true WHERE b.isbn = :isbn")
    void checkInBook(@Param("isbn") String isbn);
}
