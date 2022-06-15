package ua.hillel.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hillel.bookstore.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}