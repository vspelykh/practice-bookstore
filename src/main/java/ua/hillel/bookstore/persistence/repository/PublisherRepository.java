package ua.hillel.bookstore.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.hillel.bookstore.persistence.entity.Publisher;

@Repository
@Transactional(readOnly = true)
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

}