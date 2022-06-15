package ua.hillel.bookstore.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ua.hillel.bookstore.model.Book;

@Transactional(readOnly = true)
public interface JpaBookRepository extends JpaRepository<Book, Integer> {
}