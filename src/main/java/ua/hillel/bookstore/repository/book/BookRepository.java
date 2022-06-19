package ua.hillel.bookstore.repository.book;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ua.hillel.bookstore.model.Book;

import java.util.List;

public interface BookRepository {

    Book save(Book book);

    boolean delete(int id);

    Book get(int id);

    List<Book> getAll();

    Page<Book> findAll(BooleanExpression build, Pageable pageable);

    Page<Book> findAll(PageRequest of);
}
