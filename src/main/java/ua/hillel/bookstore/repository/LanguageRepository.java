package ua.hillel.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hillel.bookstore.model.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
}