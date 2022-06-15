package ua.hillel.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hillel.bookstore.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}