package ua.hillel.bookstore.repository.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ua.hillel.bookstore.model.Book;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository{

    private final JpaBookRepository repository;

    @Override
    public Book save(Book book) {
        return repository.save(book);
    }

    @Override
    public boolean delete(int id) {
        repository.delete(get(id));
        return true;
    }

    @Override
    public Book get(int id) {
        return repository.getReferenceById(id);
    }

    @Override
    public List<Book> getAll() {
        return repository.findAll();
    }
}
