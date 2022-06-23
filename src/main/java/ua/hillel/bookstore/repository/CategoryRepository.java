package ua.hillel.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hillel.bookstore.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}