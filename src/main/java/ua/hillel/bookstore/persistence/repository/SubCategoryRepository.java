package ua.hillel.bookstore.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hillel.bookstore.persistence.entity.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
}