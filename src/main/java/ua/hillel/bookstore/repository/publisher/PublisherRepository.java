package ua.hillel.bookstore.repository.publisher;

import ua.hillel.bookstore.model.Author;
import ua.hillel.bookstore.model.Publisher;

import java.util.List;

public interface PublisherRepository {

    Publisher save(Publisher publisher);

    boolean delete(int id);

    List<Publisher> getAll();

    Publisher get(int id);
}
