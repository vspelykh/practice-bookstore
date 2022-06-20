package ua.hillel.bookstore.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hillel.bookstore.persistence.entity.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
}