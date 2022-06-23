package ua.hillel.bookstore.repository.publisher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.hillel.bookstore.model.Publisher;
@Transactional(readOnly = true)
public interface JpaPublisherRepository extends JpaRepository<Publisher, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Publisher p WHERE p.id=:id")
    int delete(@Param("id") int id);
}