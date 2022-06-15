package ua.hillel.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hillel.bookstore.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
}