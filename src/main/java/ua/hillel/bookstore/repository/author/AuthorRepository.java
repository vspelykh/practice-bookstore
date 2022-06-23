package ua.hillel.bookstore.repository.author;

import ua.hillel.bookstore.model.Author;

import java.util.List;

public interface AuthorRepository {

    Author save(Author author);

    boolean delete(int id);

    List<Author> getAll();

    Author get(int id);
}
