package ua.hillel.bookstore.repository.book;

import ua.hillel.bookstore.dto.BookDTO;
import ua.hillel.bookstore.model.Book;

import java.util.List;

public interface BookRepository {

    Book save(Book book);

    boolean delete(int id);

    Book get(int id);

    List<Book> getAll();

    BookDTO getByVendorCode(int vendorCode);
}
