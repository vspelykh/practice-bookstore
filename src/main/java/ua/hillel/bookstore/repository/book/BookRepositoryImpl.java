package ua.hillel.bookstore.repository.book;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ua.hillel.bookstore.model.Book;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final JpaBookRepository repository;

    @Override
    public Book save(Book book) {
        return repository.save(book);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public Book get(int id) {
        return repository.getReferenceById(id);
    }

    @Override
    public List<Book> getAll() {
        return repository.findAll();
    }

    public Page<Book> findAll(BooleanExpression build, Pageable pageable) {
        return repository.findAll(build, pageable);
    }

    @Override
    public Page<Book> findAll(PageRequest of) {
        return repository.findAll(of);
    }
}
