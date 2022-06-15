package ua.hillel.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hillel.bookstore.model.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
}