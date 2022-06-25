package ua.hillel.bookstore.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hillel.bookstore.persistence.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}