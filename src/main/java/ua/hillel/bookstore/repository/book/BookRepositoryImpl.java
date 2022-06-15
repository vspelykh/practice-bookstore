package ua.hillel.bookstore.repository.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ua.hillel.bookstore.dto.BookDTO;
import ua.hillel.bookstore.model.Book;

import java.util.List;
import java.util.Optional;

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

    @Override
    public BookDTO getByVendorCode(int vendorCode) {
        return repository.findByVendorCode(vendorCode);
    }
}
