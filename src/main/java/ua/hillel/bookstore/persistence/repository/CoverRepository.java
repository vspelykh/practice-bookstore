package ua.hillel.bookstore.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hillel.bookstore.persistence.entity.Cover;

public interface CoverRepository extends JpaRepository<Cover, Integer> {
}