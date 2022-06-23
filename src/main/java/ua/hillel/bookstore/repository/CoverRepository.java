package ua.hillel.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hillel.bookstore.model.Cover;

public interface CoverRepository extends JpaRepository<Cover, Integer> {
}